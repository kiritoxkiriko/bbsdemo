package com.wxm.bbsdemo.entity;

public class PostReplySpan {
    Postreply postreply;
    User postUser;
    User modifyUser;

    public PostReplySpan() {
    }

    public PostReplySpan(Postreply postreply, User postUser, User modifyUser) {
        this.postreply = postreply;
        this.postUser = postUser;
        this.modifyUser = modifyUser;
    }

    public Postreply getPostreply() {
        return postreply;
    }

    public void setPostreply(Postreply postreply) {
        this.postreply = postreply;
    }

    public User getPostUser() {
        return postUser;
    }

    public void setPostUser(User postUser) {
        this.postUser = postUser;
    }

    public User getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(User modifyUser) {
        this.modifyUser = modifyUser;
    }
}
