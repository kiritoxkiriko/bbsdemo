package com.wxm.bbsdemo.exception;

public enum ResponseCodeEnum {
    SUCCESS(200, null,"操作成功"),

    //UNLOGIN_ERROR(233,"Unlogin", "没有登录"),

    NO_TOKEN(233,"No Token","没有token"),

    OPERATE_FAIL(666,"Failed", "操作失败"),

    WRONG_REQUEST_PARAMETER(400,"Wrong Request Parameter","请求参数错误"),

    // 用户
    SAVE_INFO_FAILED(2001,"Save Info Failed", "保存信息失败"),

    GET_INFO_FAILED(2002, "Get Info Failed","获取信息失败"),

    NO_SUCH_DATA(1000,"No Such Data","无数据"),

    NO_SUCH_USER(1001,"No Such User","无此用户"),

    NO_SUCH_BOARD(1002,"No Such Board","无此板块"),

    NO_SUCH_POST(1003,"No Such Post","无此帖子"),

    EXIST_USERNAME(1004,"Exist Username","用户名已被使用"),

    EXIST_NICKNAME(1005,"Exist Nickname","昵称已被使用"),

    EXIST_MAIL(1006,"Exist Mail","用户名已被使用"),


    NO_AUTHORITY(999,"No AuthorityService","您没有权限"),

    WRONG_PASSWORD(3001,"Wrong Password","密码错误"),

    WRONG_USERNAME(3002,"Wrong Username","账号不存在"),

    ALREADY_BLOCKED(3003,"Already Blocked","已经被屏蔽"),

    NOT_BLOCKED(3004,"Not Blocked","还没有被屏蔽"),

    WRONG_TOKEN(6666,"Wrog Token","Token 错误"),

    TOKEN_OUT_OF_DATE(7777,"Token Out of Date","Token 过期"),

    CANNOT_ADD_SECOND_MODERATOR(2003,"Cannot Add Second Moderator","不能添加两个版主"),

    ALREADY_IS_MODERATOR(2004,"Already is Moderator","已经是版主了"),

    ALREADY_IS_ASSISTANT_MODERATOR(2005,"Already is Assistant Moderator","已经是小版主了"),

    NOT_MODERATOR_OF_THIS_BOARD(2006,"Not Moderator of This Board","不是这个板块的版主"),

    NOT_ASSISTANT_MODERATOR_OF_THIS_BOARD(2007,"Not Assistant Moderator of This Board","不是这个板块的小版主"),

    NOT_POAST_OF_THIS_BOARD(2008,"Not Post of This Board","不是这个版块的帖子"),

    CANNOT_SEND_MESSAGE_TO_YOURSEOLF(4001,"Cannot Send Message to Yourself","不能给自己发消息");

    private Integer code;
    private String error;
    private String message;

    ResponseCodeEnum(Integer code, String error, String message) {
        this.code = code;
        this.error = error;
        this.message = message;
    }

    final public Integer getCode() {
        return code;
    }

    final public void setCode(Integer code) {
        this.code = code;
    }

    final public String getMessage() {
        return message;
    }

    final public void setMessage(String message) {
        this.message = message;
    }

    final public String getError() {
        return error;
    }

    final public void setError(String error) {
        this.error = error;
    }
}
