package com.wxm.bbsdemo.controller;

import com.wxm.bbsdemo.annotation.LoginRequire;
import com.wxm.bbsdemo.entity.*;
import com.wxm.bbsdemo.service.BoardService;
import com.wxm.bbsdemo.service.PostReplyService;
import com.wxm.bbsdemo.service.PostService;
import com.wxm.bbsdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostCotroller {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostReplyService postReplyService;
    @Autowired
    private BoardService boardService;
    @RequestMapping( "/{postId}")
    public String getPost(Model model, @PathVariable("postId")Long postId,  @RequestParam(value = "page",required = false)Integer pageNum){
        if(pageNum==null||pageNum<=1){
            pageNum=1;
        }
        Post post=boardService.getPost(postId);
        if(post==null){
            return "delete-post";
        }
        List<PostReplySpan> postReplySpans=new ArrayList<>();
        Page<Postreply> postPage=postService.getPostReplys(postId,pageNum);
        Board board=boardService.getBoard(post.getBoardid());
        if(postPage.getPageItems()==null||postPage.getPageItems().isEmpty()){
            return "delete-post";
        }
        for (Postreply pr:postPage.getPageItems()){
            User postUser=userService.getUserByUserId(pr.getReplybyId());
            User modifyUser=userService.getUserByUserId(pr.getModifybyId());
            PostReplySpan postReplySpan=new PostReplySpan(pr,postUser,modifyUser);
            postReplySpans.add(postReplySpan);
        }
        Page<PostReplySpan> postReplySpanPage=new Page<>(postPage.getCurrentPage(),postPage.getPageSize(),postPage.getTotalItemCount(),postReplySpans);
        model.addAttribute("page",postReplySpanPage);
        model.addAttribute("post",post);
        model.addAttribute("board",board);
        return "post";
    }
}
