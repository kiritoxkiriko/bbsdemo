package com.wxm.bbsdemo.controller;

import com.wxm.bbsdemo.entity.*;
import com.wxm.bbsdemo.service.BoardService;
import com.wxm.bbsdemo.service.PostService;
import com.wxm.bbsdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @RequestMapping(value = "/{boardId}",method = RequestMethod.GET)
    public String getBoard(Model model,@PathVariable(value = "boardId")Long boardId, @RequestParam(value = "page",required = false)Integer pageNum){
        if(pageNum==null||pageNum<=1){
            pageNum=1;
        }
        List<PostSpan> postSpans=new ArrayList<>();
        Page<Post> postPage=boardService.getPosts(boardId,pageNum);
        Board board=boardService.getBoard(boardId);
        for(Post p:postPage.getPageItems()){
            User user=userService.getUserByUserId(p.getPostbyId());
            Postreply latestPostreply=postService.getLatestPostreply(p.getPostid());
            Posttheme posttheme=postService.getPostTheme(p.getThemeid());
            User latestUser= null;
            if (latestPostreply!=null) {
                latestUser = userService.getUserByUserId(latestPostreply.getReplybyId());
            }
            PostSpan postSpan=new PostSpan(posttheme,p,user,latestPostreply,latestUser);
            postSpans.add(postSpan);
        }
        Page<PostSpan> postSpanPage=new Page<>(postPage.getCurrentPage(),postPage.getPageSize(),postPage.getTotalItemCount(),postSpans);
        System.out.println(postSpanPage);
        model.addAttribute("postSpanPage",postSpanPage);
        model.addAttribute("board",board);
        return "board";
    }
}
