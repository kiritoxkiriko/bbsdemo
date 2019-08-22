package com.wxm.bbsdemo.controller;

import com.wxm.bbsdemo.annotation.LoginRequire;
import com.wxm.bbsdemo.entity.*;
import com.wxm.bbsdemo.service.BoardService;
import com.wxm.bbsdemo.service.PostReplyService;
import com.wxm.bbsdemo.service.PostService;
import com.wxm.bbsdemo.service.UserService;
import com.wxm.bbsdemo.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/postReply")
public class PostReplyAPIController {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    PostReplyService postReplyService;
    @Autowired
    BoardService boardService;
    @LoginRequire
    @RequestMapping("/doReply")
    public ResponseData doPost(@RequestHeader(value = "Authorization")String token, @RequestParam(value = "postid")Long postId, @RequestParam(value = "content")String content){
        Long userId= TokenUtil.getUserId(token);
        Postreply postreply=new Postreply();
        postreply.setReplybyId(userId);
        postreply.setPostid(postId);
        postreply.setContent(content);
        return new ResponseData(postService.reply(postreply));
    }
}
