package com.wxm.bbsdemo.service;

import com.wxm.bbsdemo.entity.Page;
import com.wxm.bbsdemo.entity.Post;
import com.wxm.bbsdemo.entity.Postreply;
import com.wxm.bbsdemo.entity.Posttheme;
import com.wxm.bbsdemo.exception.BaseApiException;

import java.util.List;

public interface PostService {
    public boolean modifyPost(Post post)throws BaseApiException;

    public boolean reply(Postreply postreply)throws BaseApiException;

    public boolean deletePostReply(Long postreplyId)throws BaseApiException;

    public List<Postreply> getPostReplys(Long postId)throws BaseApiException;

    public Page<Postreply> getPostReplys(Long postId, int currentPage, int pageSize)throws BaseApiException;

    public Page<Postreply> getPostReplys(Long postId, int currentPage)throws BaseApiException;

    public long countPostReply(Long postId);

    public Postreply getLatestPostreply(Long postId);

    public List<Posttheme> getPostThemes(Long boardId);

    public Posttheme getPostTheme(Long themeId);

}
