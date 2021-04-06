package com.jccd.user.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "添加用户", description = "添加用户")
public class UserInfo implements Serializable {
    @ApiModelProperty(value = "用户ID【修改时填入】", position = 3)
    private Integer id;
    @ApiModelProperty(value = "用户名称", position = 3)
    private String name;
    @ApiModelProperty(value = "性别", position = 4)
    private Integer gender;
    @ApiModelProperty(value = "成绩", position = 5)
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", score=" + score +
                '}';
    }
}
