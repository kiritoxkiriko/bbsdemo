package com.wxm.bbsdemo.controller;

import com.wxm.bbsdemo.annotation.LoginRequire;
import com.wxm.bbsdemo.entity.ResponseData;
import com.wxm.bbsdemo.entity.User;
import com.wxm.bbsdemo.exception.BaseApiException;
import com.wxm.bbsdemo.exception.ResponseCodeEnum;
import com.wxm.bbsdemo.service.UserService;
import com.wxm.bbsdemo.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")

public class UserAPIController {
    @Autowired
    UserService userService;
    @RequestMapping("/getUser")
    public ResponseData getUser(@RequestParam(value = "userid",required = false)Long userId,@RequestParam(value = "username",required = false)String userName,@RequestParam(value = "nickname",required = false)String nickName){
        User user=new User();
        user.setNickname(nickName);
        user.setUserid(userId);
        user.setUsername(userName);
        List<User> userList= userService.find(user);
        if (userList==null||userList.isEmpty()){
            throw new BaseApiException(ResponseCodeEnum.NO_SUCH_DATA);
        }
        System.out.println("--------------------------------------------");
        System.out.println("/api/user/getUser :"+userList);
        return new ResponseData(userList);
    }
    @LoginRequire
    @RequestMapping("/my")
    public ResponseData getUserById(@RequestHeader(value = "Authorization")String token){
        Long userId= TokenUtil.getUserId(token);
        User user=userService.getUserByUserId(userId);
        if(user==null||userId<=0){
            throw  new BaseApiException(ResponseCodeEnum.NO_SUCH_USER);
        }
        return new ResponseData(user);
    }
}
