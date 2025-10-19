package com.hubu.handler;/*
 * @Auther:long
 * @Date:2025/7/5
 * @Description:全局异常处理器
 * @VERSON:1.8
 */

import com.hubu.exception.BaseException;
import com.hubu.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //捕获业务异常
    @ExceptionHandler(value = BaseException.class)
    public Result<String> LoginExceptionHandler(BaseException e){
        log.error("异常信息：{}",e.getMessage());
        return Result.error(e.getMessage());
    }
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(Exception e){
        log.error("错误{}",e.getMessage());
        return Result.error(e.getMessage());
    }
}
