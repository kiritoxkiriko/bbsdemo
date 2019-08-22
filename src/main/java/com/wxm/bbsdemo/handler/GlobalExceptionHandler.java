package com.wxm.bbsdemo.handler;

import com.wxm.bbsdemo.entity.ResponseData;
import com.wxm.bbsdemo.exception.BaseApiException;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(value = Exception.class)
    public ResponseData defaultErrorHandler(Exception e) throws RuntimeException{
        logger.error("", e);
        ResponseData r = new ResponseData();
        r.setMessage(e.getMessage());
        if (e instanceof NoHandlerFoundException) {
            r.setCode(404);
            r.setError("Not Found");
            r.setMessage(e.getMessage());
        } else if(e instanceof MethodArgumentTypeMismatchException){
            r.setCode(400);
            r.setError("Wrong Request Parameter");
            r.setMessage("请求参数错误");
        }else if(e instanceof MissingServletRequestParameterException){
            r.setCode(400);
            r.setError("Wrong Request Parameter");
            r.setMessage("请求参数错误");
        }
        else {
            r.setCode(500);
            r.setError("Internal Server Error");
            r.setMessage("内部错误");
        }
        r.setStatus(false);
        r.setData(null);
        return r;
    }
    @ExceptionHandler(value = BaseApiException.class)
    public ResponseData baseBusinessExceptionHandler(BaseApiException e){
        ResponseData r=new ResponseData();
        r.setCode(e.getCode());
        r.setMessage(e.getMessage());
        r.setError(e.getError());
        r.setStatus(false);
        r.setData(null);
        System.out.println(e.getMessage());
        return r;
    }
}