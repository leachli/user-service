package com.jccd.user.service.enums;

public enum ResultEnum implements ResponseCode{
    SUCCESS("10000","SUCCESS"),
    ACCOUNT_NOT_EXIST("10001", "用户不存在"),
    LOAD_DATA_EXCEPTION("10002","缓存加载数据异常"),
    SAVE_DATA_EXCEPTION("10003","添加数据失败"),
    USER_ID_NOT_NULL("10004","用户ID不能为空"),
    USER_NAME_NOT_NULL("10005","用户名不能为空"),
    USER_GENDER_NOT_NULL("10006","用户性别不能为空"),
    USER_SCORE_NOT_NULL("10006","用户成绩不能为空"),
    ACCOUNT_EXIST("10007", "用户已存在"),
    ;
    private final String code;
    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
