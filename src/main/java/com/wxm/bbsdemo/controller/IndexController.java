package com.wxm.bbsdemo.controller;

import com.wxm.bbsdemo.entity.Board;
import com.wxm.bbsdemo.entity.BoardSpan;
import com.wxm.bbsdemo.entity.Post;
import com.wxm.bbsdemo.entity.User;
import com.wxm.bbsdemo.service.BoardService;
import com.wxm.bbsdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    UserService userService;
    @Autowired
    BoardService boardService;
    @RequestMapping("/")
    public String index(Model model){
        List<Board> boardList=boardService.getAll();
        List<BoardSpan> boardSpanList=new ArrayList<>();
        for (Board b:boardList){
            Post latestPost=boardService.getLatestPost(b.getBoardid());
            User latestPostUser= null;
            if (latestPost!=null) {
                latestPostUser = userService.getUserByUserId(latestPost.getPostbyId());
            }
            User moderator=boardService.getModerator(b.getBoardid());
            List<User> assistantModerator=boardService.getAssistantModerators(b.getBoardid());
            BoardSpan boardSpan=new BoardSpan(b,moderator,assistantModerator,latestPost,latestPostUser);
            boardSpanList.add(boardSpan);
        }
        long totalPost=boardService.countPost();
        long totalPostReply=boardService.countPostReply();
        long totalUser=userService.countUser();
        System.out.println(boardSpanList);
        model.addAttribute("boardSpanList",boardSpanList);
        model.addAttribute("totalPost",totalPost);
        model.addAttribute("totalPostReply",totalPostReply);
        model.addAttribute("totalPostReply",totalPostReply);
        model.addAttribute("totalUser",totalUser);

        return "index";
    }
}
