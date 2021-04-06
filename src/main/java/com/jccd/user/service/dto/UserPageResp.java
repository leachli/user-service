package com.jccd.user.service.dto;

import com.jccd.user.service.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @program user-service
 * @description:
 * @author: LiQ
 * @create: 2021/04/06 12:24
 */
@ApiModel("查询结果")
public class UserPageResp implements Serializable {
    @ApiModelProperty("查询结果列表")
    private List<UserDto> list;
    @ApiModelProperty("总页数")
    private int totalPage;
    @ApiModelProperty("总条数")
    private long totalCount;

    public List<UserDto> getList() {
        return list;
    }

    public void setList(List<UserDto> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public UserPageResp(){

    }
    @Override
    public String toString() {
        return "UserPageResp{" +
                "list=" + list +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                '}';
    }
}
