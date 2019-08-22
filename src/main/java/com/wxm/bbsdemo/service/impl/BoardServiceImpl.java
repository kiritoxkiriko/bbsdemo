package com.wxm.bbsdemo.service.impl;

import com.github.pagehelper.PageHelper;
import com.wxm.bbsdemo.entity.*;
import com.wxm.bbsdemo.exception.BaseApiException;
import com.wxm.bbsdemo.exception.ResponseCodeEnum;
import com.wxm.bbsdemo.mapper.BoardMapper;
import com.wxm.bbsdemo.mapper.PostMapper;
import com.wxm.bbsdemo.mapper.PostreplyMapper;
import com.wxm.bbsdemo.mapper.UserMapper;
import com.wxm.bbsdemo.service.BoardService;
import com.wxm.bbsdemo.service.PostService;
import com.wxm.bbsdemo.utility.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class BoardServiceImpl implements BoardService {
    final static int PAGE_SIZE=20;
    final static int SEARCH_PAGE_SIZE=10;
    @Autowired
    BoardMapper boardMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    BoardExample boardExample;
    @Autowired
    UserExample userExample;
    @Autowired
    PostMapper postMapper;
    @Autowired
    PostExample postExample;
    @Autowired
    PostreplyMapper postreplyMapper;
    @Autowired
    PostreplyExample postreplyExample;
    @Autowired
    PostService postService;
    @Override
    public List<Board> getAll() throws BaseApiException {
        boardExample.clear();
        boardExample.or().andBoardidGreaterThan(0l);
        return boardMapper.selectByExample(boardExample);
    }

    @Override
    public Board getBoard(Long boardId) throws BaseApiException{
        if(boardId<=0){
            return null;
        }
        return boardMapper.selectByPrimaryKey(boardId);
    }

    @Override
    public boolean deleteBoard(Long boardId)  throws BaseApiException{
        if(boardId<=0){
            return false;
        }
        return boardMapper.deleteByPrimaryKey(boardId)>0?true:false;
    }

    @Override
    public boolean insertBoard(Board board) throws BaseApiException{
        board.setPostNum(0l);
        board.setPostreplyNum(0l);
        return boardMapper.insert(board)>0?true:false;
    }

    @Override
    public boolean modifyBoard(Board board) throws BaseApiException{
        return boardMapper.updateByPrimaryKeySelective(board)>0?true:false;
    }

    @Override
    public boolean addModerator(Long boardId, Long userId) throws BaseApiException{
        //判断是否存在版主
        userExample.clear();
        userExample.or().andModeratorOfEqualTo(boardId);
        if(userMapper.countByExample(userExample)>0){
            new BaseApiException(ResponseCodeEnum.CANNOT_ADD_SECOND_MODERATOR);
        }
        User user=userMapper.selectByPrimaryKey(userId);
        if(user==null){
            return false;
        }
        Long boardId1=user.getModeratorOf();
        Long boardId2=user.getAssistantModeratorOf();
        if(boardId2!=null&&boardId1!=null){
            new BaseApiException(ResponseCodeEnum.ALREADY_IS_MODERATOR);
        }
        user.setModeratorOf(boardId);
        return userMapper.updateByPrimaryKeySelective(user)>0?true:false;
    }

    @Override
    public User getModerator(Long boardId) throws BaseApiException{
        userExample.clear();
        userExample.or().andModeratorOfEqualTo(boardId);
        List<User> userList=userMapper.selectByExample(userExample);
        if(userList==null||userList.isEmpty()){
            return null;
        }
        return userList.get(0);
    }

    @Override
    public boolean addAssistantModerator(Long boardId, Long userId)throws BaseApiException{
        User user=userMapper.selectByPrimaryKey(userId);
        if(user==null){
            return false;
        }
        Long boardId1=user.getModeratorOf();
        Long boardId2=user.getAssistantModeratorOf();
        if(boardId2!=null&&boardId1!=null){
            new BaseApiException(ResponseCodeEnum.ALREADY_IS_ASSISTANT_MODERATOR);
        }
        user.setAssistantModeratorOf(boardId);
        return userMapper.updateByPrimaryKeySelective(user)>0?true:false;
    }

    @Override
    public List<User> getAssistantModerators(Long boardId) throws BaseApiException{
        userExample.clear();
        userExample.or().andAssistantModeratorOfEqualTo(boardId);
        List<User> userList=userMapper.selectByExample(userExample);
        return userList;
    }

    @Override
    public boolean deleteAssistantModerator(Long boardId, Long userId)  throws BaseApiException{
        User user=userMapper.selectByPrimaryKey(userId);
        if(user==null){
            return false;
        }
        if(!user.getAssistantModeratorOf().equals(boardId)){
            new BaseApiException(ResponseCodeEnum.NOT_ASSISTANT_MODERATOR_OF_THIS_BOARD);
        }
        user.setAssistantModeratorOf(null);
        return userMapper.updateByPrimaryKeySelective(user)>0?true:false;
    }

    @Override
    public boolean deleteModerator(Long boardId, Long userId) throws BaseApiException{
        User user=userMapper.selectByPrimaryKey(userId);
        if(user==null){
            return false;
        }
        if(!user.getModeratorOf().equals(boardId)){
            new BaseApiException(ResponseCodeEnum.NOT_MODERATOR_OF_THIS_BOARD);
        }
        user.setModeratorOf(null);
        return userMapper.updateByPrimaryKeySelective(user)>0?true:false;
    }

    @Override
    public Post getPost(Long postId) throws BaseApiException {
        Post post= postMapper.selectByPrimaryKey(postId);
        if(post==null){
            return null;
        }
        post.setViewcount(post.getViewcount()+1);
        return post;
    }

    @Override
    public Page<Post> getPosts(Long boardId, int currentPage) throws BaseApiException {
        postExample.clear();
        postExample.or().andBoardidEqualTo(boardId);
        PageHelper.startPage(currentPage,PAGE_SIZE);
        List<Post> posts=postMapper.selectByExample(postExample);
        int count=(int)postMapper.countByExample(postExample);
        return new Page<Post>(currentPage,PAGE_SIZE,count,posts);
    }

    @Override
    public Page<Post> getPosts(Long boardId, int currentPage, int pageSize) throws BaseApiException {
        postExample.clear();
        postExample.or().andBoardidEqualTo(boardId);
        PageHelper.startPage(currentPage,pageSize);
        List<Post> posts=postMapper.selectByExample(postExample);
        int count=(int)postMapper.countByExample(postExample);
        return new Page<Post>(currentPage,pageSize,count,posts);
    }

    @Override
    public List<Post> getPosts(Long boardId)throws BaseApiException{
        postExample.clear();
        postExample.or().andBoardidEqualTo(boardId);
        return postMapper.selectByExample(postExample);
    }

    @Override
    public List<Post> getPostsByUserId(Long userId) {
        postExample.clear();
        postExample.or().andPostbyIdEqualTo(userId);
        return postMapper.selectByExample(postExample);
    }

    @Override
    public Page<Post> getPostsByUserId(Long userId, int currentPage) {
        postExample.clear();
        postExample.or().andPostbyIdEqualTo(userId);
        PageHelper.startPage(currentPage,10);
        List<Post> posts=postMapper.selectByExample(postExample);
        int count=(int)postMapper.countByExample(postExample);
        return new Page<Post>(currentPage,10,count,posts);
    }

    @Override
    public Page<Post> getPostsByUserId(Long userId, int currentPage, int pageSize) {
        postExample.clear();
        postExample.or().andPostbyIdEqualTo(userId);
        PageHelper.startPage(currentPage,pageSize);
        List<Post> posts=postMapper.selectByExample(postExample);
        int count=(int)postMapper.countByExample(postExample);
        return new Page<Post>(currentPage,pageSize,count,posts);
    }

    @Override
    public Post getLatestPostByUserId(Long userId){
        postExample.clear();
        postExample.or().andPostbyIdEqualTo(userId);
        postExample.setOrderByClause("postdate DESC");
        List<Post> posts=postMapper.selectByExample(postExample);
        if(posts==null||posts.isEmpty()){
            return null;
        }
        return posts.get(0);
    }

    @Override
    public Post getLatestPost(Long boardId) {
        postExample.clear();
        postExample.or().andBoardidEqualTo(boardId);
        postExample.setOrderByClause("postdate DESC");
        List<Post> posts=postMapper.selectByExample(postExample);
        if(posts==null||posts.isEmpty()){
            return null;
        }
        Post post=posts.get(0);
        return post;
    }

    @Override
    public boolean post(Post post,String postreplyContent)  throws BaseApiException{
        if(post.getPostbyId()<=0){
            return false;
        }
        post.setViewcount(0l);
        post.setPostreplyNum(0l);
        post.setPostdate(new DateReform());
        if(postMapper.insert(post)<=0){
            return false;
        }
        //
        Board board=boardMapper.selectByPrimaryKey(post.getBoardid());
        board.setPostNum(board.getPostNum()+1);
        boardMapper.updateByPrimaryKeySelective(board);

        Board total=boardMapper.selectByPrimaryKey(0l);
        total.setPostNum(board.getPostNum()+1);
        boardMapper.updateByPrimaryKeySelective(total);
        //
        Long userId=post.getPostbyId();
        Postreply postreply=new Postreply();
        postreply.setContent(postreplyContent);
        postreply.setReplybyId(userId);
        Post latestPost=getLatestPostByUserId(post.getPostbyId());
        postreply.setPostid(latestPost.getPostid());
        return postService.reply(postreply);
    }

    @Override
    public boolean deletePost(Long boardId, Long postId)  throws BaseApiException{
        if(boardId<=0){
            return false;
        }
        Post post=postMapper.selectByPrimaryKey(postId);
        if(post==null){
            return false;
        }
        if(!post.getBoardid().equals(boardId)){
            new BaseApiException(ResponseCodeEnum.NOT_POAST_OF_THIS_BOARD);
        }
        postreplyExample.clear();
        postreplyExample.or().andPostidEqualTo(post.getPostid());
        if(postreplyMapper.deleteByExample(postreplyExample)<=0){
            return false;
        }
        return postMapper.deleteByPrimaryKey(post.getPostid())>0?true:false;
    }

    @Override
    public long countPostReply() {
        Board total=boardMapper.selectByPrimaryKey(0l);
        if(total==null){
            return 0;
        }
        return total.getPostreplyNum();
    }

    @Override
    public long countPostReply(Long boardId) {
        Board board=boardMapper.selectByPrimaryKey(boardId);
        if(board==null){
            return 0;
        }
        return board.getPostreplyNum();
    }

    @Override
    public long countPost() {
        Board total=boardMapper.selectByPrimaryKey(0l);
        if(total==null){
            return 0;
        }
        return total.getPostNum();
    }

    @Override
    public long countPost(Long boardId) {
        Board board=boardMapper.selectByPrimaryKey(boardId);
        if(board==null){
            return 0;
        }
        return board.getPostNum();
    }

    @Override
    public Page<SearchSpan> searchPostsandReplys(String keyword,int currPage,int pageSize) {
        String[] keywords=keyword.split(" ");
        postreplyExample.clear();
        PostreplyExample.Criteria criteria= postreplyExample.or();
        for(String s:keywords){
            criteria.andContentLike("%"+s+"%");
        }
        PageHelper.startPage(currPage,pageSize);
        List<Postreply> postreplyList=postreplyMapper.selectByExample(postreplyExample);
        long count=postreplyMapper.countByExample(postreplyExample);
        if(postreplyList==null||postreplyList.isEmpty()){
            return null;
        }
        List<SearchSpan> searchSpanList=new ArrayList<>();
        for(Postreply pr:postreplyList){
            User replyUser=userMapper.selectByPrimaryKey(pr.getReplybyId());
            Post post=postMapper.selectByPrimaryKey(pr.getPostid());
            Board board=boardMapper.selectByPrimaryKey(post.getBoardid());
            SearchSpan searchSpan=new SearchSpan(post,pr,board,replyUser);
            searchSpanList.add(searchSpan);
        }
        Page<SearchSpan> page=new Page<>(currPage,pageSize,(int)count,searchSpanList);
        return page;
    }
    @Override
    public Page<SearchSpan> searchPostsandReplys(String keyword,int currPage) {
        String[] keywords=keyword.split(" ");
        postreplyExample.clear();
        PostreplyExample.Criteria criteria= postreplyExample.or();
        for(String s:keywords){
            criteria.andContentLike("%"+s+"%");
        }
        PageHelper.startPage(currPage,SEARCH_PAGE_SIZE);
        List<Postreply> postreplyList=postreplyMapper.selectByExample(postreplyExample);
        long count=postreplyMapper.countByExample(postreplyExample);
        if(postreplyList==null||postreplyList.isEmpty()){
            return null;
        }
        List<SearchSpan> searchSpanList=new ArrayList<>();
        for(Postreply pr:postreplyList){
            User replyUser=userMapper.selectByPrimaryKey(pr.getReplybyId());
            Post post=postMapper.selectByPrimaryKey(pr.getPostid());
            Board board=boardMapper.selectByPrimaryKey(post.getBoardid());
            SearchSpan searchSpan=new SearchSpan(post,pr,board,replyUser);
            searchSpanList.add(searchSpan);
        }
        Page<SearchSpan> page=new Page<>(currPage,SEARCH_PAGE_SIZE,(int)count,searchSpanList);
        return page;
    }

    @Override
    public Page<SearchSpan> searchPostsandReplysOnboard(String keyword, Long boardId, int currPage) {
        String[] keywords=keyword.split(" ");
        postreplyExample.clear();
        PostreplyExample.Criteria criteria= postreplyExample.or();
        for(String s:keywords){
            criteria.andContentLike("%"+s+"%");
        }
        PageHelper.startPage(currPage,SEARCH_PAGE_SIZE);
        List<Postreply> postreplyList=postreplyMapper.selectByExample(postreplyExample);
        long count=postreplyMapper.countByExample(postreplyExample);
        if(postreplyList==null||postreplyList.isEmpty()){
            return null;
        }
        List<SearchSpan> searchSpanList=new ArrayList<>();
        for(Postreply pr:postreplyList){
            User replyUser=userMapper.selectByPrimaryKey(pr.getReplybyId());
            Post post=postMapper.selectByPrimaryKey(pr.getPostid());
            Board board=boardMapper.selectByPrimaryKey(post.getBoardid());
            SearchSpan searchSpan=new SearchSpan(post,pr,board,replyUser);
            searchSpanList.add(searchSpan);
        }
        Page<SearchSpan> page=new Page<>(currPage,SEARCH_PAGE_SIZE,(int)count,searchSpanList);
        return page;
    }

    @Override
    public Page<SearchSpan> searchPostsandReplysOnboard(String keyword, Long boardId, int currPage, int pageSize) {
        return null;
    }
}
