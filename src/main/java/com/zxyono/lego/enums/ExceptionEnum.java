package com.zxyono.lego.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {
    BAD_REQUEST_FOR_WECHAT_URL(40010, "wechat exception", "请求微信接口失败"),
    REQUEST_OPENID_FAIL(40011, "wechat exception", "获取openid失败，微信返回的错误信息："),
    OPENID_URL_INVALID(40066, "wechat exception", "openid请求地址错误"),
    JS_CODE_INVALID(40029,"wechat exception", "js_code格式错误"),
    JS_CODE_USED(40163, "wechat exception", "js_code已被使用"),

    PARAM_EXCEPTION(40001, "param exception", "参数错误"),
    NOT_LOGIN(40301, "authentication exception", "未登录"),
    ACCESS_DENIED(40302, "authentication exception", "拒绝访问"),
    JWT_EXCEPTION(40305, "authentication exception", "JWT异常"),
    JWT_SIGNATURE_EXCEPTION(40306, "authentication exception", "JWT签名不匹配"),
    JWT_CREATE_EXCEPTION(40307, "authentication exception", "Jwt code创建失败"),
    WX_AUTH_FAILED(50002, "wx exception", "小程序权限认证失败"),
    JSON_PARSE_EXCEPTION(50003, "json parse exception", "Json解析失败"),

    USER_NOT_FOUND(50001, "login exception", "用户不存在"),
    PASSWORD_WRONG(50002, "login exception", "密码错误"),
    USERNAME_NULL_EXCEPTION(50003, "login exception", "用户名不能为空"),
    PASSWORD_NULL_EXCEPTION(50004, "login exception", "密码不能为空"),

    NOT_HAVE_ENTITY_ID(50101, "param exception", "未传递实体Id"),

    REDIS_SEVER_NOT_RUN(60001, "redis exception", "redis缓存服务器未启动"),
    MYSQL_UNIQUE_COLUMNS(60101, "mysql exception","数据库Unique约束的列不能有重复值"),
    CANT_DELETE_YOURSELF(60102, "mysql exception", "不能删除自己");

    private Integer code;
    private String type;
    private String message;

    public ExceptionEnum addMessage(String message) {
        this.message = message;
        return this;
    }

    public ExceptionEnum addMessage(String message, Object...args) {
        this.message = String.format(message, args);
        return this;
    }

    public ExceptionEnum addMoreMessage(String message) {
        this.message = this.message + message;
        return this;
    }

}