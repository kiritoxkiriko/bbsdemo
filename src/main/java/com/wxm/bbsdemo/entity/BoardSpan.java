package com.wxm.bbsdemo.entity;

import com.wxm.bbsdemo.mapper.UserMapper;
import com.wxm.bbsdemo.service.BoardService;
import com.wxm.bbsdemo.service.PostService;
import com.wxm.bbsdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BoardSpan {
    private Board board;
    private User moderator;
    private List<User> assistantModerators;
    private Post latestPost;
    private User latestPostUser;

    public BoardSpan() {

    }

    public BoardSpan(Board board, User moderator, List<User> assistantModerators, Post latestPost, User latestPostUser) {
        this.board = board;
        this.moderator = moderator;
        this.assistantModerators = assistantModerators;
        this.latestPost = latestPost;
        this.latestPostUser = latestPostUser;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public User getModerator() {
        return moderator;
    }

    public void setModerator(User moderator) {
        this.moderator = moderator;
    }

    public List<User> getAssistantModerators() {
        return assistantModerators;
    }

    public void setAssistantModerators(List<User> assistantModerators) {
        this.assistantModerators = assistantModerators;
    }

    public Post getLatestPost() {
        return latestPost;
    }

    public void setLatestPost(Post latestPost) {
        this.latestPost = latestPost;
    }

    public User getLatestPostUser() {
        return latestPostUser;
    }

    public void setLatestPostUser(User latestPostUser) {
        this.latestPostUser = latestPostUser;
    }

    @Override
    public String toString() {
        return "BoardSpan{" +
                "board=" + board +
                ", moderator=" + moderator +
                ", assistantModerators=" + assistantModerators +
                ", latestPost=" + latestPost +
                ", latestPostUser=" + latestPostUser +
                '}';
    }
}
