package com.game.zhujuan.controller.handler;

import com.game.zhujuan.common.Result;
import com.game.zhujuan.controller.handler.exception.ParamCheckException;
import com.game.zhujuan.util.WebContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ParamCheckException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result paramCheckException(ParamCheckException exp) {
        HttpServletRequest request = WebContext.getRequest();
        String uri = request.getRequestURI();
        log.error("请求异常 {}, {}", uri, exp);
        return Result.error(exp.getCode(), exp.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result exception(Exception exp) {
        HttpServletRequest request = WebContext.getRequest();
        String uri = request.getRequestURI();
        log.error("请求异常 {}, {}", uri, exp);
        return Result.error(exp.getMessage());
    }
}
