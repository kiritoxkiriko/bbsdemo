package com.wxm.bbsdemo.exception;

public class BaseApiException extends RuntimeException{
    private Integer code;
    private String error;
    private String message;

    public BaseApiException(ResponseCodeEnum responseCodeEnum) {
        this.code = responseCodeEnum.getCode();
        this.message=responseCodeEnum.getMessage();
        this.error=responseCodeEnum.getError();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
