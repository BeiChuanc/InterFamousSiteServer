package cn.beichuan.famous_site_server.utils;

import lombok.Data;

import java.util.Map;

/// 返回结果类
@Data
public class R<T> {

    /// 响应码
    ///
    /// 200：成功  0:失败
    private Integer code;

    /// 成功与否
    private boolean success;

    /// 错误信息
    private String message;

    /// 数据
    private T data;

    /// 动态条件
    private Map<String, Object> condition;

    /// 成功响应
    public static <T> R<T> success(T object, String message) {
        R<T> r = new R<>();
        r.data = object;
        r.code = 200;
        r.message = message;
        r.success = true;
        return r;
    }

    /// 默认成功响应
    public static <T> R<T> success(T object) {
        R<T> r = new R<>();
        r.data = object;
        r.code = 200;
        r.message = "响应成功！";
        r.success = true;
        return r;
    }

    /// 默认成功响应
    public static <T> R<T> success(String message) {
        R<T> r = new R<>();
        r.code = 200;
        r.message = message;
        r.success = true;
        return r;
    }

    /// 错误响应
    public static <T> R<T> error(String message) {
        R<T> r = new R<>();
        r.message = message;
        r.code = 1;
        r.success = false;
        return r;
    }

    /// 自定义错误响应码
    public static <T> R<T> error(Integer code, String message) {
        R<T> r = new R<>();
        r.message = message;
        r.code = code;
        r.success = false;
        return r;
    }

    /// 设置条件
    /// @param condition 条件Map
    public R<T> setCondition(Map<String, Object> condition) {
        this.condition = condition;
        return this;
    }
}
