package com.wxm.bbsdemo.entity;

public class SenderSpan {
    private User sender;
    private int unreadNum;

    public SenderSpan() {
    }

    public SenderSpan(User sender, int unreadNum) {
        this.sender = sender;
        this.unreadNum = unreadNum;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public int getUnreadNum() {
        return unreadNum;
    }

    @Override
    public String toString() {
        return "SenderSpan{" +
                "sender=" + sender +
                ", unreadNum=" + unreadNum +
                '}';
    }

    public void setUnreadNum(int unreadNum) {
        this.unreadNum = unreadNum;
    }
}
