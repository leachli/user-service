package com.jccd.user.service.service;

import com.jccd.user.service.constants.ResultResponse;
import com.jccd.user.service.dto.UserDto;
import com.jccd.user.service.dto.UserInfo;
import com.jccd.user.service.dto.UserPageResp;
import com.jccd.user.service.dto.UserReqParam;
import com.jccd.user.service.entity.User;
import com.jccd.user.service.enums.ResultEnum;
import com.jccd.user.service.enums.StringStatus;
import com.jccd.user.service.repository.UserRepository;
import com.jccd.user.service.util.DateUtil;
import com.jccd.user.service.util.JsonUtil;
import com.jccd.user.service.util.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    private static final String USER_CACHE = "user:cache:%s";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisUtil redisUtil;

    public ResultResponse findPageUser(UserReqParam reqParam) {
        UserPageResp pageResp = new UserPageResp();
        Specification specs = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            Predicate p4 = criteriaBuilder.equal(root.get("del"), StringStatus.VALID.getStatus());
            list.add(p4);
            if (!StringUtils.isEmpty(reqParam.getName())) {
                Predicate p1 = criteriaBuilder.like(root.get("name"), "%" + reqParam.getName() + "%");
                list.add(p1);
            }
            if (reqParam.getScore() != null) {
                Predicate p2 = criteriaBuilder.lessThan(root.get("score"), reqParam.getScore());
                list.add(p2);
            }
            if (reqParam.getGender() != null) {
                Predicate p3 = criteriaBuilder.equal(root.get("gender"), reqParam.getGender());
                list.add(p3);
            }

            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        };
        Pageable pageable = PageRequest.of(reqParam.getPage() - 1, reqParam.getPageSize());
        Page userPage = userRepository.findAll(specs, pageable);
        if (userPage != null && !CollectionUtils.isEmpty(userPage.getContent())) {
            List<UserDto> list = new ArrayList<>();
            List<User> userList = userPage.getContent();
            userList.forEach(o -> {
                UserDto dto = new UserDto();
                BeanUtils.copyProperties(o, dto);
                dto.setCreateTime(DateUtil.format(o.getCreateTime(),DateUtil.YYYYMMDDHHMMSS3));
                dto.setUpdateTime(DateUtil.format(o.getUpdateTime(),DateUtil.YYYYMMDDHHMMSS3));
                list.add(dto);
            });
            pageResp.setList(list);
            pageResp.setTotalPage(userPage.getTotalPages());
            pageResp.setTotalCount(userPage.getTotalElements());
        }
        return ResultResponse.ok(pageResp);
    }


    public ResultResponse<UserDto> findById(Integer id) {
        String redisKey = String.format(USER_CACHE, id);
        User user;
        String value = redisUtil.get(redisKey);
        if (!StringUtils.isEmpty(value)) {
            user = JsonUtil.toObject(value, User.class);
        } else {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            } else {
                return ResultResponse.fail(ResultEnum.ACCOUNT_NOT_EXIST);
            }
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        userDto.setCreateTime(DateUtil.format(user.getCreateTime(),DateUtil.YYYYMMDDHHMMSS3));
        userDto.setUpdateTime(DateUtil.format(user.getUpdateTime(),DateUtil.YYYYMMDDHHMMSS3));
        return ResultResponse.ok(userDto);

    }

    @Transactional
    public ResultResponse addUser(UserInfo userInfo) {
        if (StringUtils.isEmpty(userInfo.getName())) {
            return ResultResponse.fail(ResultEnum.USER_NAME_NOT_NULL);
        }
        if (userInfo.getGender() == null) {
            return ResultResponse.fail(ResultEnum.USER_GENDER_NOT_NULL);
        }

        if (userInfo.getScore() == null) {
            return ResultResponse.fail(ResultEnum.USER_SCORE_NOT_NULL);
        }
        List<User> userList = userRepository.findByNameAndDel(userInfo.getName(),StringStatus.VALID.getStatus());
        if (!CollectionUtils.isEmpty(userList)){
            return ResultResponse.fail(ResultEnum.ACCOUNT_EXIST);
        }
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        user.setDel(StringStatus.VALID.getStatus());
        user.setCreateTime(new Date());
        user.setVersion(1);
        User saveUser = userRepository.save(user);
        if (saveUser.getId() != null) {
            String redisKey = String.format(USER_CACHE, saveUser.getId());
            redisUtil.setEx(redisKey, JsonUtil.toJson(saveUser), 1,TimeUnit.MINUTES);
            return ResultResponse.ok(true);
        }
        return ResultResponse.fail(ResultEnum.SAVE_DATA_EXCEPTION);
    }

    @Transactional
    public ResultResponse updateUser(UserInfo userInfo) {
        if (userInfo.getId() == null) {
            return ResultResponse.fail(ResultEnum.USER_ID_NOT_NULL);
        }
        if (!StringUtils.isEmpty(userInfo.getName())){
            List<User> userList = userRepository.findByNameAndDel(userInfo.getName(),StringStatus.VALID.getStatus());
            if (!CollectionUtils.isEmpty(userList)){
                return ResultResponse.fail(ResultEnum.ACCOUNT_EXIST);
            }
        }
        Optional<User> optionalUser = userRepository.findById(userInfo.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setId(userInfo.getId());
            if (userInfo.getScore() != null) {
                user.setScore(userInfo.getScore());
            }
            if (userInfo.getGender() != null) {
                user.setGender(userInfo.getGender());
            }
            if (!StringUtils.isEmpty(userInfo.getName())) {
                user.setName(userInfo.getName());
            }
            User saveUser = userRepository.save(user);
            String redisKey = String.format(USER_CACHE, user.getId());
            redisUtil.setEx(redisKey, JsonUtil.toJson(saveUser),1,TimeUnit.MINUTES);
            return ResultResponse.ok(true);
        }else {
            return ResultResponse.fail(ResultEnum.USER_ID_NOT_NULL);
        }

    }

    public ResultResponse deleteUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String redisKey = String.format(USER_CACHE, id);
            user.setDel(StringStatus.INVALID.getStatus());
            User saveUser = userRepository.save(user);
            redisUtil.setEx(redisKey, JsonUtil.toJson(saveUser),1,TimeUnit.MINUTES);
            return ResultResponse.ok(true);
        }
        return ResultResponse.fail(ResultEnum.ACCOUNT_NOT_EXIST);
    }
}
