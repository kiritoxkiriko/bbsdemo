package com.wxm.bbsdemo.entity;


import java.util.List;

public class ResponseData {
    private Boolean status;
    private Integer code;
    private String error;
    private String message;
    private Object data;

    public ResponseData() {
        status=true;
        code=200;
        message="操作成功";
    }

    public ResponseData(Object data) {
        this();
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data=data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "status=" + status +
                ", code=" + code +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
