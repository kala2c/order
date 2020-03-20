package com.order.exception.handler;


import com.order.domain.Result;
import com.order.exception.AppException;
import com.order.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Object> handle(Exception e)
    {
        if (e instanceof AppException) {
            AppException appException = (AppException) e;
            return ResultUtil.create(appException.getCode(), appException.getMessage(), null);
        } else {
            return ResultUtil.create(500, e.getMessage(), null);
        }
    }
}
