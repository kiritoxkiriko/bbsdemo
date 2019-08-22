package com.wxm.bbsdemo.controller;

import com.wxm.bbsdemo.annotation.LoginRequire;
import com.wxm.bbsdemo.entity.Board;
import com.wxm.bbsdemo.entity.Post;
import com.wxm.bbsdemo.entity.Postreply;
import com.wxm.bbsdemo.entity.ResponseData;
import com.wxm.bbsdemo.exception.BaseApiException;
import com.wxm.bbsdemo.exception.ResponseCodeEnum;
import com.wxm.bbsdemo.service.*;
import com.wxm.bbsdemo.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manage")
public class ManageAPIController {
    @Autowired
    UserService userService;
    @Autowired
    BoardService boardService;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    PostService postService;
    @Autowired
    PostReplyService postReplyService;

    @LoginRequire
    @RequestMapping("/deletePost")
    public ResponseData deletePost(@RequestHeader(value = "Authorization")String token, @RequestParam(value = "postid")Long postId, @RequestParam("boardid")Long boardId){
        Long loginUserId= TokenUtil.getUserId(token);
        if (!authorityService.hasRightDeletePost(loginUserId,postId)) {
            throw new BaseApiException(ResponseCodeEnum.NO_AUTHORITY);
        }
        return new ResponseData(boardService.deletePost(boardId,postId));
    }
    @LoginRequire
    @RequestMapping("/deleteReply")
    public ResponseData deleteReply(@RequestHeader(value = "Authorization")String token, @RequestParam("replyid")Long replyId){
        Long loginUserId= TokenUtil.getUserId(token);
        if (!authorityService.hasRightDeletePostReply(loginUserId,replyId)) {
            throw new BaseApiException(ResponseCodeEnum.NO_AUTHORITY);
        }
        return new ResponseData(postService.deletePostReply(replyId));
    }
    /*@LoginRequire
    @RequestMapping("/blockUser")
    public ResponseData blockUser(@RequestHeader(value = "Authorization")String token,@RequestParam("userid")Long userId){

    }
    @LoginRequire
    @RequestMapping("/unblockUser")
    public ResponseData unblockUser(@RequestHeader(value = "Authorization")String token,@RequestParam("userid")Long userId){

    }*/
    @LoginRequire
    @RequestMapping("/deletBoard")
    public ResponseData deletBoard(@RequestHeader(value = "Authorization")String token,@RequestParam("boardid")Long boardId){
        Long loginUserId= TokenUtil.getUserId(token);
        if (!authorityService.isAdmin(loginUserId)) {
            throw new BaseApiException(ResponseCodeEnum.NO_AUTHORITY);
        }
        return new ResponseData(boardService.deleteBoard(boardId));
    }
    @LoginRequire
    @RequestMapping("/modifyBoard")
    public ResponseData modifyBoard(@RequestHeader(value = "Authorization")String token,@RequestParam("boardid")Long boardId,@RequestParam(value = "notice",required = false)String notice,@RequestParam(value = "infor",required = false)String infor){
        Long loginUserId= TokenUtil.getUserId(token);
        if (!authorityService.hasRightModifyBoard(loginUserId,boardId)) {
            throw new BaseApiException(ResponseCodeEnum.NO_AUTHORITY);
        }
        Board board=boardService.getBoard(boardId);
        if(infor!=null){
            board.setInfo(infor);
        }else if(notice!=null){
            board.setNotice(notice);
        }
        return new ResponseData(boardService.modifyBoard(board));
    }
    @LoginRequire
    @RequestMapping("/editModerator")
    public ResponseData addModerator(@RequestHeader(value = "Authorization")String token,@RequestParam("boardid")Long boardId,@RequestParam("userid")Long userId,@RequestParam("isdelete")boolean isDelete){
        Long loginUserId= TokenUtil.getUserId(token);
        if (!authorityService.hasRightEditModerator(loginUserId,boardId)) {
            throw new BaseApiException(ResponseCodeEnum.NO_AUTHORITY);
        }
        if(isDelete){
            return new ResponseData(boardService.deleteModerator(boardId,userId));
        }else{
            return new ResponseData(boardService.addModerator(boardId,userId));
        }

    }

    @LoginRequire
    @RequestMapping("/editAssistantModerator")
    public ResponseData deleteAssistantModerator(@RequestHeader(value = "Authorization")String token,@RequestParam("boardid")Long boardId,@RequestParam("userid")Long userId,@RequestParam("isdelete")boolean isDelete){
        Long loginUserId= TokenUtil.getUserId(token);
        if (!authorityService.hasRightEditAssistantModerator(loginUserId,boardId)) {
            throw new BaseApiException(ResponseCodeEnum.NO_AUTHORITY);
        }
        if (isDelete) {
            return new ResponseData(boardService.deleteAssistantModerator(boardId,userId));
        }else {
            return new ResponseData(boardService.addAssistantModerator(boardId,userId));
        }
    }

    @LoginRequire
    @RequestMapping("/editReply")
    public ResponseData deleteAssistantModerator(@RequestHeader(value = "Authorization")String token,@RequestParam("replyid")Long replyId,@RequestParam("content")String content){
        Long loginUserId= TokenUtil.getUserId(token);
        if (!authorityService.hasRightModifyPostReply(loginUserId,replyId) ){
            throw new BaseApiException(ResponseCodeEnum.NO_AUTHORITY);
        }
        Postreply postreply=new Postreply();
        postreply.setContent(content);
        postreply.setPostreplyId(replyId);
        postreply.setModifybyId(loginUserId);
        return new ResponseData(postReplyService.modifyPostReply(postreply));
    }
    @LoginRequire
    @RequestMapping("/hasRightEditReply")
    public ResponseData hasRightModifyPost(@RequestHeader(value = "Authorization")String token,@RequestParam("replyid")Long replyId){
        Long loginUserId= TokenUtil.getUserId(token);
        if (!authorityService.hasRightModifyPostReply(loginUserId,replyId) ){
            throw new BaseApiException(ResponseCodeEnum.NO_AUTHORITY);
        }else{
            return new ResponseData(true);
        }
    }

}
