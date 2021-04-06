package com.jccd.user.service.enums;

public enum StringStatus {
    /**
     * 有效
     */
    VALID("1", "有效"),
    /**
     * 无效
     */
    INVALID("2", "无效");

    private String status;
    private String desc;

    StringStatus(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
