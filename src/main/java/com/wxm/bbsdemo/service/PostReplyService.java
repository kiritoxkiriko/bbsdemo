package com.wxm.bbsdemo.service;

import com.wxm.bbsdemo.entity.Page;
import com.wxm.bbsdemo.entity.Postreply;
import com.wxm.bbsdemo.entity.PostreplyComment;
import com.wxm.bbsdemo.exception.BaseApiException;

import java.util.List;

public interface PostReplyService {

    public boolean modifyPostReply(Postreply postreply)throws BaseApiException;

    public List<PostreplyComment> getPostReplyComments(Long postReplyId)throws BaseApiException;

    public Page<PostreplyComment> getPostReplyComments(Long postReplyId,int currentPage)throws BaseApiException;

    public Page<PostreplyComment> getPostReplyComments(Long postReplyId,int currentPage,int pageSize)throws BaseApiException;

    public boolean comment(PostreplyComment postreplyComment)throws BaseApiException;

    public boolean deleteComment(Long postReplyCommentId)throws BaseApiException;

    public long countPostReplyComment(Long postReplyId);
}
