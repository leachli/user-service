package com.jccd.user.service.controller;

import com.jccd.user.service.constants.ResultResponse;
import com.jccd.user.service.dto.UserDto;
import com.jccd.user.service.dto.UserInfo;
import com.jccd.user.service.dto.UserPageResp;
import com.jccd.user.service.dto.UserReqParam;
import com.jccd.user.service.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户操作")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findUserPage", method = RequestMethod.POST)
    @ApiOperation(value = "分页获取用户数据", notes = "分页获取用户数据")
    public ResultResponse<UserPageResp> findUserPage(
            @ApiParam(value = "查询条件")
            @RequestBody UserReqParam reqParam) {
        return userService.findPageUser(reqParam);
    }


    @RequestMapping(value = "/findUserById",method = RequestMethod.GET)
    @ApiOperation(value = "根据用户ID查询", notes = "根据用户ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", dataType = "Integer")
    })
    public ResultResponse<UserDto> findUserPage(Integer id) {
        return userService.findById(id);
    }


    @RequestMapping(value = "/addUser",method = RequestMethod.PUT)
    @ApiOperation(value = "添加用户", notes = "添加用户")
    public ResultResponse<Boolean> addUser(@RequestBody  UserInfo info) {
        return userService.addUser(info);
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.PUT)
    @ApiOperation(value = "修改用户", notes = "修改用户")
    public ResultResponse<Boolean> updateUser(@RequestBody  UserInfo info) {
        return userService.updateUser(info);
    }


    @RequestMapping(value = "/deleteUser",method = RequestMethod.DELETE)
    @ApiOperation(value = "逻辑删除用户", notes = "逻辑删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", dataType = "Integer")
    })
    public ResultResponse<Boolean> deleteUser(Integer id) {
        return userService.deleteUser(id);
    }

}
