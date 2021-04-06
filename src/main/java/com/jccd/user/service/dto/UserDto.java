package com.jccd.user.service.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "用户信息", description = "用户信息")
public class UserDto implements Serializable {
    @ApiModelProperty(value = "用户Id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "版本", position = 2)
    private Integer version;
    @ApiModelProperty(value = "用户名称", position = 3)
    private String name;
    @ApiModelProperty(value = "性别", position = 4)
    private Integer gender;
    @ApiModelProperty(value = "成绩", position = 5)
    private Integer score;
    @ApiModelProperty(value = "创建时间", position = 6)
    private String createTime;
    @ApiModelProperty(value = "最后一次修改时间", position = 7)
    private String updateTime;
    @ApiModelProperty(value = "是否删除",position = 8)
    private String del;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", score=" + score +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", del=" + del +
                '}';
    }
}
