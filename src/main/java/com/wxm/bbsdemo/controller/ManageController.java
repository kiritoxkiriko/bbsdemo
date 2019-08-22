package com.wxm.bbsdemo.controller;

import com.wxm.bbsdemo.entity.User;
import com.wxm.bbsdemo.service.AuthorityService;
import com.wxm.bbsdemo.service.BoardService;
import com.wxm.bbsdemo.service.UserService;
import com.wxm.bbsdemo.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class ManageController {
    @Autowired
    UserService userService;
    @Autowired
    BoardService boardService;
    @Autowired
    AuthorityService authorityService;
    @RequestMapping("/manage")
    public String manage(@RequestParam("token") String token, Model model){
        Long loginUserId=null;
        if(token!=null&&!token.equals("")){
            if (TokenUtil.verify(token)) {
                loginUserId=TokenUtil.getUserId(token);
            }else {
                return "redirect:/";
            }
        }else {
            return "redirect:/";
        }
        User user=userService.getUserByUserId(loginUserId);
        int level=0;
        if(authorityService.isAdmin(loginUserId)){
            level=3;
        }else if(user.getModeratorOf()!=null){
            level=2;
        }else if(user.getAssistantModeratorOf()!=null){
            level=1;
        }else{
            level=0;
        }
        model.addAttribute("level",level);
        model.addAttribute("user",user);
        return "manage";
    }
}
