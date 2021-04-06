package com.jccd.user.service.constants;


import com.jccd.user.service.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel( description = "返回报文")
public class ResultResponse<T> implements Serializable {
    @ApiModelProperty(value = "是否成功标识")
    private boolean success;
    @ApiModelProperty(value = "返回错误码")
    private String code;
    @ApiModelProperty(value = "返回错误信息")
    private String message;
    @ApiModelProperty(value = "返回报文，T是返回格式,参考传入格式类型")
    private T data;

    /**
     * 构建请求成功的返回报文
     * 常用用于客户端请求数据新建和更新操作，提示操作成功
     *
     * @return
     */
    public static ResultResponse ok() {
        return ResultResponse.ok(ResultEnum.SUCCESS);
    }

    /**
     * 构建请求成功的返回报文，包含返回码和操作消息字段
     * 常用用于客户端请求数据新建和更新操作，提示操作成功
     *
     * @param resultEnum 操作提示消息
     * @return
     */
    public static ResultResponse ok(ResultEnum resultEnum) {
        return ResultResponse.of(true, resultEnum.getCode(), resultEnum.getMessage(), null);
    }

    /**
     * 构建请求成功的返回报文，包含message消息字段和data数据字段
     *
     * @param data 请求表单或列表数据
     * @return
     */
    public static <T> ResultResponse ok(T data) {
        return ResultResponse.ok(ResultEnum.SUCCESS, data);
    }

    /**
     * 构建请求成功的返回报文，包含返回码和操作消息字段和data数据字段
     *
     * @param resultEnum 操作提示消息
     * @param data         请求表单或列表数据
     * @return
     */
    public static <T> ResultResponse ok(ResultEnum resultEnum, T data) {
        return ResultResponse.of(true, resultEnum.getCode(), resultEnum.getMessage(), data);
    }

    /**
     * 构建请求失败的返回报文，包含错误码信息和message消息字段
     *
     * @param resultEnum 错误码
     * @return
     */
    public static ResultResponse fail(ResultEnum resultEnum) {
        return ResultResponse.of(false, resultEnum.getCode(), resultEnum.getMessage(), null);
    }

    /**
     * 构建请求失败的返回报文，包含错误码信息、消息字段和data数据字段
     * 通常来说，不常用，失败一般只需要返回对应的错误码和消息字段即可
     *
     * @param resultEnum 错误码
     * @param data         错误数据详情
     * @return
     */
    public static <T> ResultResponse fail(ResultEnum resultEnum, T data) {
        return ResultResponse.of(false, resultEnum.getCode(), resultEnum.getMessage(), data);
    }

    private static <T> ResultResponse of(boolean success, String code, String message, T data) {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setSuccess(success);
        resultResponse.setCode(code);
        resultResponse.setMessage(message);
        resultResponse.setData(data);
        return resultResponse;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
