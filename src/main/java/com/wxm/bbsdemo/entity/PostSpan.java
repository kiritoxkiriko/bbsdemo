package com.wxm.bbsdemo.entity;

import java.util.List;

public class PostSpan{
    private Posttheme posttheme;
    private Post post;
    private User postUser;
    private Postreply latestPostReply;
    private User latestPostUser;
    public PostSpan() {
    }

    public PostSpan(Posttheme posttheme,Post post, User postUser, Postreply latestPostReply, User latestPostUser) {
        this.posttheme=posttheme;
        this.post = post;
        this.postUser = postUser;
        this.latestPostReply = latestPostReply;
        this.latestPostUser = latestPostUser;
    }

    public User getPostUser() {
        return postUser;
    }

    public void setPostUser(User postUser) {
        this.postUser = postUser;
    }

    public User getLatestPostUser() {
        return latestPostUser;
    }

    public void setLatestPostUser(User latestPostUser) {
        this.latestPostUser = latestPostUser;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Postreply getLatestPostReply() {
        return latestPostReply;
    }

    public void setLatestPostReply(Postreply latestPostReply) {
        this.latestPostReply = latestPostReply;
    }

    public Posttheme getPosttheme() {
        return posttheme;
    }

    public void setPosttheme(Posttheme posttheme) {
        this.posttheme = posttheme;
    }

    @Override
    public String toString() {
        return "PostSpan{" +
                "posttheme=" + posttheme +
                ", post=" + post +
                ", postUser=" + postUser +
                ", latestPostReply=" + latestPostReply +
                ", latestPostUser=" + latestPostUser +
                '}';
    }
}
