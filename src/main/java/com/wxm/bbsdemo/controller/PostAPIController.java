package com.wxm.bbsdemo.controller;

import com.wxm.bbsdemo.annotation.LoginRequire;
import com.wxm.bbsdemo.entity.Post;
import com.wxm.bbsdemo.entity.ResponseData;
import com.wxm.bbsdemo.exception.BaseApiException;
import com.wxm.bbsdemo.exception.ResponseCodeEnum;
import com.wxm.bbsdemo.service.BoardService;
import com.wxm.bbsdemo.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostAPIController {
    @Autowired
    BoardService boardService;
    @RequestMapping("/")
    public ResponseData getPost(@RequestParam(value = "postid") Long postId){
        Post post=boardService.getPost(postId);
        if(post==null){
            throw new BaseApiException(ResponseCodeEnum.NO_SUCH_DATA);
        }
        return new ResponseData(boardService.getPost(postId));
    }
    @LoginRequire
    @RequestMapping("/doPost")
    public ResponseData doPost(@RequestHeader(value = "Authorization")String token, @RequestParam(value = "boardid")Long boardId, @RequestParam(value = "name")String name, @RequestParam(value = "content")String content){
        Long userId=TokenUtil.getUserId(token);
        Post post=new Post();
        post.setBoardid(boardId);
        post.setName(name);
        post.setThemeid(1L);
        post.setPostbyId(userId);
        return new ResponseData(boardService.post(post,content));
    }

    @RequestMapping("/getUserPost")
    public ResponseData getUserPosts(@RequestParam(value = "userid") Long userId){
        return new ResponseData(boardService.getPostsByUserId(userId));
    }
}
