package com.wxm.bbsdemo.controller;

import com.wxm.bbsdemo.annotation.LoginRequire;
import com.wxm.bbsdemo.entity.Message;
import com.wxm.bbsdemo.entity.ResponseData;
import com.wxm.bbsdemo.entity.User;
import com.wxm.bbsdemo.exception.BaseApiException;
import com.wxm.bbsdemo.exception.ResponseCodeEnum;
import com.wxm.bbsdemo.service.MessageService;
import com.wxm.bbsdemo.service.UserService;
import com.wxm.bbsdemo.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageAPIController {
    @Autowired
    private MessageService messageService;
    @LoginRequire
    @RequestMapping("/getMessages")
    public ResponseData getMessages (@RequestHeader(value = "Authorization")String token, @RequestParam("senderid")Long senderId){
        Long reciverId=TokenUtil.getUserId(token);
        List<Message> messages=messageService.getMessages(reciverId,senderId);
        return new ResponseData(messages);
    }

    @LoginRequire
    @RequestMapping("/sendMessage")
    public ResponseData sendMessage(@RequestHeader(value = "Authorization")String token, @RequestParam("receiverid")Long receiverId,@RequestParam("content")String content){
        Long senderId=TokenUtil.getUserId(token);
        if(senderId.equals(receiverId)){
            throw new BaseApiException(ResponseCodeEnum.CANNOT_SEND_MESSAGE_TO_YOURSEOLF);
        }
        Message message=new Message();
        message.setReceiverid(receiverId);
        message.setSenderid(senderId);
        message.setMessage(content);
        if(!messageService.sendMessage(message)){
            throw new BaseApiException(ResponseCodeEnum.OPERATE_FAIL);
        }else {
            return new ResponseData(true);
        }
    }

    @LoginRequire
    @RequestMapping("/unreadMessage")
    public  ResponseData unreadMessage(@RequestHeader(value = "Authorization")String token){
        Long userId=TokenUtil.getUserId(token);
        return new ResponseData(messageService.countUnreadMessage(userId));
    }
}
