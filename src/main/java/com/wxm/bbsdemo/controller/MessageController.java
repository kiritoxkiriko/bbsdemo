package com.wxm.bbsdemo.controller;

import com.wxm.bbsdemo.entity.Message;
import com.wxm.bbsdemo.entity.SenderSpan;
import com.wxm.bbsdemo.entity.User;
import com.wxm.bbsdemo.exception.BaseApiException;
import com.wxm.bbsdemo.exception.ResponseCodeEnum;
import com.wxm.bbsdemo.service.MessageService;
import com.wxm.bbsdemo.service.UserService;
import com.wxm.bbsdemo.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @RequestMapping("/message")
    public String message(@RequestParam("token") String token,@RequestParam(value = "receiverid",required = false)Long receiverId, Model model){
        User receiver=null;
        if(receiverId!=null){
            receiver=userService.getUserByUserId(receiverId);
            if(receiver==null||receiverId<=0){
                throw new BaseApiException(ResponseCodeEnum.NO_SUCH_USER);
            }
        }
        Long loginUserId=null;
        if(token!=null&&!token.equals("")){
            if (TokenUtil.verify(token)) {
                loginUserId=TokenUtil.getUserId(token);
            }else {
                return "redirect:/";
            }
        }else {
            return "redirect:/";
        }
        //User user=userService.getUserByUserId(loginUserId);
        List<SenderSpan> senderSpans=new LinkedList<>();
        Map<Long, List<Message>> messageMap=messageService.getMessages(loginUserId);
        if (messageMap!=null) {
            for (Long userid:messageMap.keySet()){
                int count=0;
                User user=userService.getUserByUserId(userid);
                if(user!=null){
                    for(Message msg:messageMap.get(userid)){
                        if(!msg.getIsReceived()&&msg.getReceiverid().equals(loginUserId)){
                            count++;
                        }
                    }
                    senderSpans.add(new SenderSpan(user,count));
                }
            }
        }
        if(receiver!=null){
            if( messageMap==null){
                senderSpans.add(new SenderSpan(receiver,0));
            }else {
                if(!messageMap.containsKey(receiverId)){
                    ((LinkedList<SenderSpan>) senderSpans).push(new SenderSpan(receiver,0));
                }
            }
        }
        System.out.println(senderSpans);
        model.addAttribute("receiver",receiver);
        model.addAttribute("senderSpans",senderSpans);
        return "message";
    }
}
