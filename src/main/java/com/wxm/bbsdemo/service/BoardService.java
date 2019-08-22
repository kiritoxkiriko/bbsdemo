package com.wxm.bbsdemo.service;

import com.wxm.bbsdemo.entity.*;
import com.wxm.bbsdemo.exception.BaseApiException;

import java.util.List;

public interface BoardService {

    public List<Board> getAll()throws BaseApiException;

    public Board getBoard(Long boardId)throws BaseApiException;

    public boolean deleteBoard(Long boardId)throws BaseApiException;

    public boolean insertBoard(Board board)throws BaseApiException;

    public boolean modifyBoard(Board board)throws BaseApiException;

    public boolean addModerator(Long boardId,Long userId)throws BaseApiException;

    public User getModerator(Long boardId)throws BaseApiException;

    public boolean addAssistantModerator(Long boardId,Long userId)throws BaseApiException;

    public List<User> getAssistantModerators(Long boardId)throws BaseApiException;

    public boolean deleteAssistantModerator(Long boardId,Long userId)throws BaseApiException;

    public boolean deleteModerator(Long boardId,Long userId)throws BaseApiException;

    public Post getPost(Long postId)throws BaseApiException;

    public List<Post> getPosts(Long boardId)throws BaseApiException;

    public Page<Post> getPosts(Long boardId,int currentPage)throws BaseApiException;

    public Page<Post> getPosts(Long boardId,int currentPage,int pageSize)throws BaseApiException;

    public List<Post> getPostsByUserId(Long userId);

    public Page<Post> getPostsByUserId(Long userId,int currentPage);

    public Page<Post> getPostsByUserId(Long userId,int currentPage,int pageSize);

    public Post getLatestPostByUserId(Long userId);

    public Post getLatestPost(Long boardId);

    public boolean post(Post post, String postreplyContent)throws BaseApiException;

    public boolean deletePost(Long boardId,Long postId)throws BaseApiException;

    public long countPost();

    public long countPost(Long boardId);

    public long countPostReply();

    public long countPostReply(Long boardId);

    public Page<SearchSpan> searchPostsandReplys(String keyword,int currPage,int pageSize);

    public Page<SearchSpan> searchPostsandReplys(String keyword,int currPage);

    public Page<SearchSpan> searchPostsandReplysOnboard(String keyword,Long boardId,int currPage);

    public Page<SearchSpan> searchPostsandReplysOnboard(String keyword,Long boardId,int currPage,int pageSize);
}
