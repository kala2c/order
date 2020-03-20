package com.order.utils;

import com.order.domain.Result;

public class ResultUtil {

    public static Result<Object> create(Integer code, String message, Object data)
    {
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result<Object> success()
    {
        return create(200, "操作成功", null);
    }

    public static Result<Object> success(Object data)
    {
        return create(200, "OK", data);
    }

    public static Result<Object> error()
    {
        return create(500, "出错了 稍后再试", null);
    }

    public Result<Object> error(Object data)
    {
        return create(500, "请求错误", data);
    }
}
