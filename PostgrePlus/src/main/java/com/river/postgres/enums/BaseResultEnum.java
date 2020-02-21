package com.river.postgres.enums;

import com.river.postgres.util.R;

/**
 * Created by dell on 2018-05-16.
 */
public enum BaseResultEnum {

    /**
     * 基础异常
     * 0-100
     */
    SUCCESS(0, "common.request.success"), // 成功
    FAIL(1, "common.request.fail"), // 失败
    PARAM_FAIL(2, "common.request.param.fail"),
    PARAM_RESOLVE_FAIL(3, "common.request.param.resolve.fail"), // 参数解析失败
    PARAM_NULL(4, "common.request.param.null"), // 参数解析失败

    /**
     * 用户异常
     * 10xx
     */
    NOT_LOGIN(1001, "common.access.login.not"),
    LOGIN_EXPIRE(1002, "common.access.login.expire"),
    USER_NOT_FOUND(1003, "common.access.user.notfound"),
    UNAUTHORIZED(1004, "common.access.unauthorized"),
    LOGIN_FAIL(1005, "common.access.login.fail"), // 登录失败次数过多
    ACCESS_TOKEN_NULL(1006, "common.access_token.notfound"),
    ACCESS_TOKEN_RESOLVE_FAIL(1006, "common.access_token.resolve.fail"),

    /**
     * 参数异常
     * 20xx-参数异常
     */
    PARAM_MISSING(2001, "common.request.param.missing"),
    PARAM_ILLEGAL(2002, "common.request.param.illegal"),
    PARAM_VALID_ERROR(2003, "common.request.param.valid.error"),

    RESOURCE_NOT_FOUND(2101, "common.resource.not.found"),

    INTERNAL_SERVER_ERROR(500, "server.internal.error"),
    /**
     * 系统异常
     * 90xx
     */
    UNKNOWN_EXCEPTION(9001, "common.request.exception.unknown"),
    ILLEGAL_REQUEST(9002, "common.request.param.illegal"),
    TIMEOUT(9011, "common.request.timeout"),
    UNAVAILABLE(9012, "common.request.unavailable"),

    /**
     * 参数异常
     * 99xx-网关异常
     */
    PARAM_TOKEN_NULL(9901, "common.request.param.token.null"), // TOKEN空
    PARAM_TOKEN_EXPIRE(9902, "common.request.param.token.expire"),   // TOKEN过期
    PARAM_TOKEN_FAIL(9903, "common.request.param.token.fail"),   // TOKEN非法
    ;

    private int code;
    private int status;
    private String msg;

    BaseResultEnum(int code, String msg) {
        this.code = code;
        this.status = code;
        this.msg = msg;
    }

    public R<?> getResultModel() {
        return new R(code, msg, null);
    }

    public String getResultJsonString() {
        return String.format("{\"code\": \"%s\", \"message\": \"%s\"}", this.code, this.msg);

    }
}
