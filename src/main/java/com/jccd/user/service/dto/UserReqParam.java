package com.jccd.user.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @program user-service
 * @description:
 * @author: LiQ
 * @create: 2021/04/06 12:16
 */
@ApiModel(value = "根据查询条件分页查询")
public class UserReqParam implements Serializable {
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称", required = true, position = 1)
    private String name;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", required = true, position = 2)
    private Integer gender;

    /**
     * 成绩
     */
    @ApiModelProperty(value = "成绩", required = true, position = 3)
    private Integer score;
    /**
     * 页码
     */
    @ApiModelProperty(value = "页码[页码不能小于0]", required = true, position = 4)
    private Integer page;
    /**
     * 页数
     */
    @ApiModelProperty(value = "页数", required = true, position = 5)
    private Integer pageSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    @Override
    public String toString() {
        return "UserReqParam{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", score='" + score + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
