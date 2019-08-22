package com.wxm.bbsdemo.service.impl;

import com.github.pagehelper.PageHelper;
import com.wxm.bbsdemo.entity.*;
import com.wxm.bbsdemo.exception.BaseApiException;
import com.wxm.bbsdemo.mapper.PostreplyCommentMapper;
import com.wxm.bbsdemo.mapper.PostreplyMapper;
import com.wxm.bbsdemo.service.PostReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostReplyServiceImpl implements PostReplyService {
    final static int PAGE_SIZE=10;

    @Autowired
    PostreplyMapper postreplyMapper;
    @Autowired
    PostreplyCommentMapper postreplyCommentMapper;
    @Autowired
    PostreplyExample postreplyExample;
    @Autowired
    PostreplyCommentExample postreplyCommentExample;
    @Override
    public boolean modifyPostReply(Postreply postreply) throws BaseApiException {
        if(postreply.getModifybyId()<=0){
            return false;
        }
        postreply.setModifydate(new DateReform());
        postreply.getModifybyId();
        return postreplyMapper.updateByPrimaryKeySelective(postreply)>0;
    }

    @Override
    public List<PostreplyComment> getPostReplyComments(Long postReplyId) throws BaseApiException {
        postreplyCommentExample.clear();
        postreplyCommentExample.or().andPostreplyIdEqualTo(postReplyId);
        return postreplyCommentMapper.selectByExample(postreplyCommentExample);
    }

    @Override
    public Page<PostreplyComment> getPostReplyComments(Long postReplyId, int currentPage) throws BaseApiException {
        PageHelper.startPage(currentPage,PAGE_SIZE);
        postreplyCommentExample.clear();
        postreplyCommentExample.or().andPostreplyIdEqualTo(postReplyId);
        List<PostreplyComment> postreplyComments=postreplyCommentMapper.selectByExample(postreplyCommentExample);
        int countNum=(int)postreplyCommentMapper.countByExample(postreplyCommentExample);
        return new Page<PostreplyComment>(currentPage,PAGE_SIZE,countNum,postreplyComments);
    }

    @Override
    public Page<PostreplyComment> getPostReplyComments(Long postReplyId, int currentPage, int pageSize) throws BaseApiException {
        PageHelper.startPage(currentPage,pageSize);
        postreplyCommentExample.clear();
        postreplyCommentExample.or().andPostreplyIdEqualTo(postReplyId);
        List<PostreplyComment> postreplyComments=postreplyCommentMapper.selectByExample(postreplyCommentExample);
        int countNum=(int)postreplyCommentMapper.countByExample(postreplyCommentExample);
        return new Page<PostreplyComment>(currentPage,pageSize,countNum,postreplyComments);
    }

    @Override
    public boolean comment(PostreplyComment postreplyComment) throws BaseApiException {
        postreplyExample.clear();
        postreplyExample.or().andPostreplyIdEqualTo(postreplyComment.getPostreplyId());
        long count=postreplyMapper.countByExample(postreplyExample);
        if(count<=0){
            return false;
        }
        postreplyComment.setCommentdate(new DateReform());
        return postreplyCommentMapper.insert(postreplyComment)>0;
    }

    @Override
    public boolean deleteComment(Long postReplyCommentId) throws BaseApiException {
        return postreplyCommentMapper.deleteByPrimaryKey(postReplyCommentId)>0;
    }

    @Override
    public long countPostReplyComment(Long postReplyId) {
        Postreply postreply=postreplyMapper.selectByPrimaryKey(postReplyId);
        if(postreply==null){
            return -1;
        }
        postreplyCommentExample.clear();
        postreplyCommentExample.or().andPostreplyIdEqualTo(postReplyId);
        return postreplyCommentMapper.countByExample(postreplyCommentExample);
    }
}
