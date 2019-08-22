package com.wxm.bbsdemo.controller;

import com.auth0.jwt.interfaces.Header;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxm.bbsdemo.annotation.LoginRequire;
import com.wxm.bbsdemo.entity.Board;
import com.wxm.bbsdemo.entity.User;
import com.wxm.bbsdemo.entity.UserInfo;
import com.wxm.bbsdemo.service.AuthorityService;
import com.wxm.bbsdemo.service.BoardService;
import com.wxm.bbsdemo.service.UserService;
import com.wxm.bbsdemo.service.VerifyService;
import com.wxm.bbsdemo.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    BoardService boardService;
    @Autowired
    AuthorityService authorityService;
    @RequestMapping("/user/{userId}")
    public String getUser(Model model, @PathVariable("userId")Long userId, @RequestParam(value = "token",required = false)String token){
        Long loginUserId=null;
        boolean isLogin=false;
        if(token!=null&&!token.equals("")){
            if (TokenUtil.verify(token)) {
                loginUserId=TokenUtil.getUserId(token);
            }else {
                loginUserId=-1l;
            }

        }
        if(loginUserId!=null&&loginUserId.equals(userId)){
            isLogin=true;
        }
        if(loginUserId==null){
            model.addAttribute("loginUserId","");
        }else {
            model.addAttribute("loginUserId",loginUserId);
        }
        User user=userService.getUserByUserId(userId);
        if(user==null||userId<=0){
            return "noUser";
        }
        UserInfo userInfo=null;
        String info=user.getInfo();
        if (info!=null&&!info.equals("")) {
            try {
                userInfo=objectMapper.readValue(info, UserInfo.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(user.getIsPrivate()&&!isLogin){
            model.addAttribute("user",user);
            model.addAttribute("isLogin",isLogin);
            return "privateUser";
        }else {
            model.addAttribute("posts",boardService.getPostsByUserId(userId));
            model.addAttribute("isLogin",isLogin);
            model.addAttribute("user",user);
            model.addAttribute("userInfo",userInfo);
            return "user";
        }
    }

}
