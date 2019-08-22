package com.wxm.bbsdemo.controller;

import com.wxm.bbsdemo.entity.Board;
import com.wxm.bbsdemo.entity.Page;
import com.wxm.bbsdemo.entity.SearchSpan;
import com.wxm.bbsdemo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class SearchController {
    @Autowired
    private BoardService boardService;
    @RequestMapping("/search")
    public String search(Model model, @RequestParam("keyword")String keyword, @RequestParam(value = "page",required = false)Integer page){
        if(page==null){
            page=1;
        }
        Page<SearchSpan> searchPage=boardService.searchPostsandReplys(keyword,page);
        model.addAttribute("page",searchPage);
        model.addAttribute("keyword",keyword);
        return "search";
    }
}
