package com.wxm.bbsdemo.service.impl;

import com.github.pagehelper.PageHelper;
import com.wxm.bbsdemo.entity.*;
import com.wxm.bbsdemo.exception.BaseApiException;
import com.wxm.bbsdemo.exception.ResponseCodeEnum;
import com.wxm.bbsdemo.mapper.*;
import com.wxm.bbsdemo.service.BoardService;
import com.wxm.bbsdemo.service.PostReplyService;
import com.wxm.bbsdemo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    final static int PAGE_SIZE=10;

    @Autowired
    PostMapper postMapper;
    @Autowired
    PostreplyMapper postreplyMapper;
    @Autowired
    PostreplyExample postreplyExample;
    @Autowired
    PostExample postExample;
    @Autowired
    BoardMapper boardMapper;
    @Autowired
    PostthemeMapper postthemeMapper;
    @Autowired
    PostthemeExample postthemeExample;
    @Autowired
    UserMapper userMapper;
    @Override
    public boolean modifyPost(Post post) throws BaseApiException {
        return postMapper.updateByPrimaryKeySelective(post)>0;
    }

    @Override
    public boolean reply(Postreply postreply) throws BaseApiException {
        if(postreply.getModifybyId()!=null&&postreply.getModifybyId().equals(0)){//0代表已经被删除
            return false;
        }
        Long postId=postreply.getPostid();
        postExample.clear();
        postExample.or().andPostidEqualTo(postId);
        if(postMapper.countByExample(postExample)<=0){
            return false;
        }
        postreply.setReplydate(new DateReform());
        postreplyExample.clear();
        postreplyExample.or().andPostidEqualTo(postId);
        postreplyExample.setOrderByClause("floornum DESC");
        long num=postreplyMapper.countByExample(postreplyExample);
        postreply.setFloornum((int)(num+1));
        //
        Post post=postMapper.selectByPrimaryKey(postreply.getPostid());
        post.setPostreplyNum(post.getPostreplyNum()+1);
        postMapper.updateByPrimaryKeySelective(post);

        Board board=boardMapper.selectByPrimaryKey(post.getBoardid());
        board.setPostreplyNum(board.getPostreplyNum()+1);
        boardMapper.updateByPrimaryKeySelective(board);

        Board total=boardMapper.selectByPrimaryKey(0l);
        total.setPostreplyNum(board.getPostreplyNum()+1);
        boardMapper.updateByPrimaryKeySelective(total);
        //
        boolean result= postreplyMapper.insert(postreply)>0;
        if(result){
            User replyUser=userMapper.selectByPrimaryKey(postreply.getReplybyId());
            User postUser=userMapper.selectByPrimaryKey(post.getPostbyId());
            replyUser.setPoint(replyUser.getPoint()+1);
            postUser.setPoint(postUser.getPoint()+1);
            if(postUser.getLevel()/10<1){
                postUser.setLevel((int)(postUser.getPoint()/100)+1);
            }
            userMapper.updateByPrimaryKeySelective(replyUser);
            userMapper.updateByPrimaryKeySelective(postUser);
        }
        return result;
    }

    @Override
    public boolean deletePostReply(Long postreplyId) throws BaseApiException {
        if(postreplyId<=0){
            return false;
        }
        Postreply postreply=postreplyMapper.selectByPrimaryKey(postreplyId);
        if(postreply==null){
            return false;
        }
        if(postreplyMapper.deleteByPrimaryKey(postreplyId)<=0){
            return false;
        }else {
            postreply.getPostid();

            postreplyExample.clear();
            postreplyExample.or().andPostidEqualTo(postreply.getPostid());
            if(postreplyMapper.countByExample(postreplyExample)<=0){
                postMapper.deleteByPrimaryKey(postreply.getPostid());
            }
            return true;
        }
    }

    @Override
    public List<Postreply> getPostReplys(Long postId) throws BaseApiException {
        Post post=postMapper.selectByPrimaryKey(postId);
        if(post==null){
            return null;
        }
        post.setViewcount(post.getViewcount()+1);
        if(postMapper.updateByPrimaryKeySelective(post)<0){
            return null;
        }
        postreplyExample.clear();
        postreplyExample.or().andPostidEqualTo(postId);
        return postreplyMapper.selectByExample(postreplyExample);
    }

    @Override
    public Page<Postreply> getPostReplys(Long postId, int currentPage, int pageSize) throws BaseApiException {
        Post post=postMapper.selectByPrimaryKey(postId);
        if(post==null){
            return null;
        }
        post.setViewcount(post.getViewcount()+1);
        if(postMapper.updateByPrimaryKeySelective(post)<0){
            return null;
        }
        PageHelper.startPage(currentPage,pageSize);
        postreplyExample.clear();
        postreplyExample.or().andPostidEqualTo(postId);
        List<Postreply> postreplies=postreplyMapper.selectByExample(postreplyExample);
        int count=(int)postreplyMapper.countByExample(postreplyExample);
        return new Page<Postreply>(currentPage,pageSize,count,postreplies);
    }

    @Override
    public Page<Postreply> getPostReplys(Long postId, int currentPage) throws BaseApiException {
        Post post=postMapper.selectByPrimaryKey(postId);
        if(post==null){
            return null;
        }
        post.setViewcount(post.getViewcount()+1);
        if(postMapper.updateByPrimaryKeySelective(post)<0){
            return null;
        }
        PageHelper.startPage(currentPage,PAGE_SIZE);
        postreplyExample.clear();
        postreplyExample.or().andPostidEqualTo(postId);
        List<Postreply> postreplies=postreplyMapper.selectByExample(postreplyExample);
        int count=(int)postreplyMapper.countByExample(postreplyExample);
        return new Page<Postreply>(currentPage,PAGE_SIZE,count,postreplies);
    }

    @Override
    public long countPostReply( Long postId) {
        Post post=postMapper.selectByPrimaryKey(postId);
        if (post==null){
            return -1;
        }
        return post.getPostreplyNum();
    }

    @Override
    public Postreply getLatestPostreply(Long postId) {
        if(postMapper.selectByPrimaryKey(postId)==null){
            new BaseApiException(ResponseCodeEnum.NO_SUCH_POST);
        }
        postreplyExample.clear();
        postreplyExample.or().andPostidEqualTo(postId);
        postreplyExample.setOrderByClause("replydate DESC");
        List<Postreply> postreplyList=postreplyMapper.selectByExample(postreplyExample);
        if(postreplyList==null||postreplyList.isEmpty()){
            return null;
        }
        return postreplyList.get(0);
    }

    @Override
    public List<Posttheme> getPostThemes(Long boardId) {
        postthemeExample.clear();
        postthemeExample.or().andBoardidEqualTo(boardId);
        return postthemeMapper.selectByExample(postthemeExample);
    }

    @Override
    public Posttheme getPostTheme(Long postThemeId) {
        return postthemeMapper.selectByPrimaryKey(postThemeId);
    }
}
