package com.wxm.bbsdemo.service.impl;

import com.wxm.bbsdemo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    JavaMailSender jms;
    @Override
    public boolean send(String mailAddress,String topic,String content){
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom("coursework-2019@wxmpoi.xyz");
        //接收者
        mainMessage.setTo(mailAddress);
        //发送的标题
        mainMessage.setSubject(topic);
        //发送的内容
        mainMessage.setText(content);
        try {
            jms.send(mainMessage);
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
