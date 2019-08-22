package com.wxm.bbsdemo.service;

public interface VerifyService {
    public String login(String userName,String password);

    public String login(Long userId,String password);

    public boolean verify(String token);

    public Long verifyAndGetUserId(String token);

    public boolean refresh(String token);

    public boolean logout(String token);
}
