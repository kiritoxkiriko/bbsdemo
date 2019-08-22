package com.wxm.bbsdemo.service.impl;

import com.wxm.bbsdemo.entity.*;
import com.wxm.bbsdemo.mapper.*;
import com.wxm.bbsdemo.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PostreplyCommentMapper postreplyCommentMapper;
    @Autowired
    private PostreplyMapper postreplyMapper;
    @Autowired
    private BannedMapper bannedMapper;
    @Autowired
    private BannedmsgMapper bannedmsgMapper;
    @Autowired
    private BannedExample bannedExample;
    @Override
    public boolean isAdmin(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return false;
        } else {
            return user.getType().equals("admin");
        }
    }
    @Override
    public boolean isModeratorOf(Long boardId,Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return false;
        } else {
            if(user.getModeratorOf()==null){
                return false;
            }
            return user.getModeratorOf().equals(boardId);
        }
    }

    @Override
    public boolean isAssistantModeratorOf(Long boardId,Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return false;
        } else {
            if(user.getAssistantModeratorOf()==null){
                return false;
            }
            return user.getAssistantModeratorOf().equals(boardId);
        }
    }

    @Override
    public boolean isPosterOf(Long postId, Long userId) {
        Post post = postMapper.selectByPrimaryKey(postId);
        if(post==null){
            return false;
        }else{
            return post.getPostbyId().equals(userId);
        }
    }

    @Override
    public boolean isReplyerOf(Long postReplyId, Long userId) {
        Postreply postReply = postreplyMapper.selectByPrimaryKey(postReplyId);
        if(postReply==null){
            return false;
        }else{
            return postReply.getReplybyId().equals(userId);
        }
    }

    @Override
    public boolean isCommenterOf(Long postReplyCommentId, Long userId) {
        PostreplyComment postreplyComment = postreplyCommentMapper.selectByPrimaryKey(postReplyCommentId);
        if(postreplyComment==null){
            return false;
        }else{
            return postreplyComment.getCommentbyId().equals(userId);
        }
    }

    @Override
    public boolean hasRightModifyBoard(Long userId, Long boardId) {
        if(isAdmin(userId)){
            return true;
        }else {
            return isModeratorOf(boardId,userId);
        }
    }

    @Override
    public boolean hasRightEditAssistantModerator(Long userId, Long boardId) {
        if(isAdmin(userId)){
            return true;
        }else {
            return isModeratorOf(boardId,userId);
        }
    }

    @Override
    public boolean hasRightEditModerator(Long userId, Long boardId) {
        return isAdmin(userId);
    }

    @Override
    public boolean hasRightModifyPost(Long userId, Long postId) {
        Post post=postMapper.selectByPrimaryKey(postId);
        if(post==null){
            return false;
        }
        if(isAdmin(userId)){
            return true;
        }else if(isModeratorOf(post.getBoardid(),userId)||isAssistantModeratorOf(post.getBoardid(),userId)){
            return true;
        }else {
            return isPosterOf(postId,userId);
        }
    }

    @Override
    public boolean hasRightModifyPostReply(Long userId, Long postReplyId) {
        Postreply postreply=postreplyMapper.selectByPrimaryKey(postReplyId);
        Long postId;
        Post post;
        if(postreply==null){
            return false;
        }else {
            post=postMapper.selectByPrimaryKey(postreply.getPostid());
        }
        if(post==null){
            return false;
        }
        postId=post.getPostid();
        if(isAdmin(userId)){
            return true;
        }else if(isModeratorOf(post.getBoardid(),userId)||isAssistantModeratorOf(post.getBoardid(),userId)){
            return true;
        }else if(isPosterOf(postId,userId)){
            return true;
        }else {
            return isReplyerOf(postReplyId,userId);
        }
    }

    @Override
    public boolean hasRightDeletePostReplyComment(Long userId, Long postReplyCommentId) {
        PostreplyComment postreplyComment=postreplyCommentMapper.selectByPrimaryKey(postReplyCommentId);
        Postreply postreply;
        Long postReplyId;
        Long postId;
        Post post;
        if(postreplyComment==null){
            return false;
        }else {
            postreply=postreplyMapper.selectByPrimaryKey(postreplyComment.getPostreplyId());
        }
        if(postreply==null){
            return false;
        }else {
            post=postMapper.selectByPrimaryKey(postreply.getPostid());
        }
        if(post==null){
            return false;
        }
        postId=post.getPostid();
        postReplyId=postreply.getPostreplyId();
        if(isAdmin(userId)){
            return true;
        }else if(isModeratorOf(post.getBoardid(),userId)||isAssistantModeratorOf(post.getBoardid(),userId)){
            return true;
        }else if(isPosterOf(postId,userId)){
            return true;
        }else if(isReplyerOf(postReplyId,userId)){
            return true;
        }else {
            return isCommenterOf(postReplyCommentId,userId);
        }
    }

    @Override
    public boolean hasRightDeletePostReply(Long userId, Long postReplyId) {
        return hasRightModifyPostReply(userId,postReplyId);
    }

    @Override
    public boolean hasRightDeletePost(Long userId, Long postId) {
        Post post=postMapper.selectByPrimaryKey(postId);
        if(post==null){
            return false;
        }
        if(isAdmin(userId)){
            return true;
        }else if(isModeratorOf(post.getBoardid(),userId)||isAssistantModeratorOf(post.getBoardid(),userId)){
            return true;
        }else{
            return post.getPostbyId().equals(userId);
        }
    }

    @Override
    public boolean hasRightBanOnBoard(Long userId, Long boardId) {
        if(isAdmin(userId)){
            return true;
        }else {
            return isModeratorOf(boardId,userId)||isAssistantModeratorOf(boardId,userId);
        }
    }

    @Override
    public boolean hasRightPostOnBoard(Long userId, Long boardId) {
        bannedExample.clear();
        bannedExample.or().andBanneduserIdEqualTo(userId).andBannedbordIdEqualTo((long) 0);
        List<Banned> bannedList1=bannedMapper.selectByExample(bannedExample);
        //判断是否被全局ban
        if(bannedList1==null||bannedList1.isEmpty()){

            bannedExample.clear();
            bannedExample.or().andBanneduserIdEqualTo(userId).andBannedbordIdEqualTo(boardId);
            List<Banned> bannedList2=bannedMapper.selectByExample(bannedExample);
            //判断是否在该板块上被ban
            if(bannedList2==null||bannedList2.isEmpty()){
                return true;
            }
            //比较当前时间和ban过期时间，如果已经过期则 删除 并返回true
            Date now=new Date(System.currentTimeMillis());
            Banned banned=bannedList2.get(0);
            if(banned.getEndtime().before(now)){
                bannedMapper.deleteByPrimaryKey(banned.getBannedid());
                return true;
            }
            return false;
        }else {
            //比较当前时间和ban过期时间，如果已经过期则 删除 并返回true
            Date now=new Date(System.currentTimeMillis());
            Banned banned=bannedList1.get(0);
            if(banned.getEndtime().before(now)){
                bannedMapper.deleteByPrimaryKey(banned.getBannedid());
                return true;
            }
            return false;
        }
    }
}
