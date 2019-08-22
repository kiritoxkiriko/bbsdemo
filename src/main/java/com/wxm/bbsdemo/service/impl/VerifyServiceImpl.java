package com.wxm.bbsdemo.service.impl;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.wxm.bbsdemo.exception.BaseApiException;
import com.wxm.bbsdemo.exception.ResponseCodeEnum;
import com.wxm.bbsdemo.service.UserService;
import com.wxm.bbsdemo.service.VerifyService;
import com.wxm.bbsdemo.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyServiceImpl implements VerifyService {

    private static long EXPIRE_TIME=12*60*60*1000;
    @Autowired
    private UserService userService;

    @Override
    public String login(String userName, String password) {
        Long userId=userService.verfiyPassword(userName,password);
        if(userId!=null){
            return TokenUtil.sign(userId,EXPIRE_TIME);
        }else {
            throw new BaseApiException(ResponseCodeEnum.WRONG_USERNAME);
        }
    }

    @Override
    public String login(Long userId, String password) {
        if(userService.verfiyPassword(userId,password)){
            return TokenUtil.sign(userId,EXPIRE_TIME);
        }else {
            throw new BaseApiException(ResponseCodeEnum.WRONG_PASSWORD);
        }
    }

    @Override
    public boolean verify(String token) {
        boolean isVerify= false;
        try {
            isVerify = TokenUtil.verify(token);
        } catch (TokenExpiredException e) {
            System.out.println(e.getMessage());
            throw new BaseApiException(ResponseCodeEnum.TOKEN_OUT_OF_DATE);
        }
        if(isVerify){
            //Long userId=TokenUtil.getUserId(token);
            return true;
        }else {
            throw new BaseApiException(ResponseCodeEnum.WRONG_TOKEN);
        }
    }

   @Override
    public Long verifyAndGetUserId(String token) {
        try{
            if(verify(token)){
                return TokenUtil.getUserId(token);
            }else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean refresh(String token) {
        return false;
    }

    @Override
    public boolean logout(String token) {
        return false;
    }
}
