package com.example.demo.domain;

/**
 * @author AuroraOps04
 * @date 2021/12/5 09:41:03
 * @description 统一响应码
 */
public enum ErrorCode {
    BUSINESS_ERROR("4000", "业务异常", 2),
    NOT_LOGIN("401", "您没有登陆,请登录", 2),
    NOT_PERMISSION("403", "您没有权限, 请联系管理员", 9);
    private final String code;
    private final String message;
    // error display type： 0 silent; 1 message.warn; 2 message.error; 4 notification; 9 page
    private final Integer showType;

    ErrorCode(String code, String message, Integer showType) {
        this.code = code;
        this.showType = showType;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Integer getShowType() {
        return showType;
    }
}
