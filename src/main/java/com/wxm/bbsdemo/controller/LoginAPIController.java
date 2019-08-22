package com.wxm.bbsdemo.controller;

import com.wxm.bbsdemo.annotation.LoginRequire;
import com.wxm.bbsdemo.entity.ResponseData;
import com.wxm.bbsdemo.entity.User;
import com.wxm.bbsdemo.exception.BaseApiException;
import com.wxm.bbsdemo.exception.ResponseCodeEnum;
import com.wxm.bbsdemo.service.MailService;
import com.wxm.bbsdemo.service.UserService;
import com.wxm.bbsdemo.service.VerifyService;
import com.wxm.bbsdemo.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginAPIController {
    private static long EXPIRE_TIME=12*60*60*1000;
    @Autowired
    private VerifyService verifyService;
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    String mailcontent="欢迎使用考研信息交流论坛，您申请注册了用户名为%s的账号,\n请点击%s激活您的账号";
    @RequestMapping("/api/login")
    public ResponseData login(@RequestParam(value = "username" )String userName,@RequestParam(value = "password")String password){
        return new ResponseData(verifyService.login(userName,password));
    }
    @RequestMapping(value = "/api/regist")
    public ResponseData regist(@RequestParam("mail") String mail,@RequestParam("nickname") String nickName,@RequestParam("username")String userName,@RequestParam("password")String password){
        if(mail.equals("")||nickName.equals("")||userName.equals("")||password.equals("")){
            throw new BaseApiException(ResponseCodeEnum.WRONG_REQUEST_PARAMETER);
        }
        switch (userService.exist(userName,nickName,mail)){
            case 1:
                throw new BaseApiException(ResponseCodeEnum.EXIST_USERNAME);
            case 2:
                throw new BaseApiException(ResponseCodeEnum.EXIST_NICKNAME);
            case 3:
                throw new BaseApiException(ResponseCodeEnum.EXIST_MAIL);
            default:break;
        }
        User user=new User();
        user.setMail(mail);
        user.setPassword(password);
        user.setNickname(nickName);
        user.setUsername(userName);

        if(!userService.insert(user)){
            throw new BaseApiException(ResponseCodeEnum.OPERATE_FAIL);
        }
        User user1=new User();
        user1.setUsername(userName);
        User find=userService.find(user1).get(0);
        userService.activate(find.getUserid());
        String token=TokenUtil.sign(find.getUserid(),EXPIRE_TIME);
        return new ResponseData(token);
    }

}
