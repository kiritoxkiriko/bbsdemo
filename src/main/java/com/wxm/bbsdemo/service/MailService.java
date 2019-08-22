package com.wxm.bbsdemo.service;

public interface MailService {
    public boolean send(String mailAddress,String topic,String content);
}
