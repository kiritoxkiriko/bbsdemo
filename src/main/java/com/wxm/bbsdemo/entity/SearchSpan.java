package com.wxm.bbsdemo.entity;

public class SearchSpan {
    private Post post;
    private Postreply postreply;
    private Board board;
    private User replyUser;

    public SearchSpan() {
    }

    public SearchSpan(Post post, Postreply postreply, Board board, User replyUser) {
        this.post = post;
        this.postreply = postreply;
        this.board = board;
        this.replyUser = replyUser;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Postreply getPostreply() {
        return postreply;
    }

    public void setPostreply(Postreply postreply) {
        this.postreply = postreply;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public User getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(User replyUser) {
        this.replyUser = replyUser;
    }
}
