package com.wxm.bbsdemo.service.impl;

import com.wxm.bbsdemo.entity.*;
import com.wxm.bbsdemo.exception.BaseApiException;
import com.wxm.bbsdemo.exception.ResponseCodeEnum;
import com.wxm.bbsdemo.mapper.BannedmsgMapper;
import com.wxm.bbsdemo.mapper.MessageMapper;
import com.wxm.bbsdemo.mapper.UserMapper;
import com.wxm.bbsdemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.lang.reflect.Array;
import java.util.*;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    MessageExample messageExample;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserExample userExample;
    @Autowired
    BannedmsgExample bannedmsgExample;
    @Autowired
    BannedmsgMapper bannedmsgMapper;
    @Override
    public boolean sendMessage(Message message) throws BaseApiException {
        if(message.getSenderid()<=0){
            return false;
        }
        userExample.clear();
        userExample.or().andUseridEqualTo(message.getSenderid());
        long count=userMapper.countByExample(userExample);
        if (count<=0){
            throw new BaseApiException(ResponseCodeEnum.NO_SUCH_USER);
        }
        message.setSenddate(new DateReform());
        message.setIsNotice(false);
        message.setIsReceived(false);
        return messageMapper.insert(message)>0;
    }

    @Override
    public boolean sendMessageBySystem(Message message) {
        message.setSenderid(0l);
        userExample.clear();
        userExample.or().andUseridEqualTo(message.getSenderid());
        long count=userMapper.countByExample(userExample);
        if (count<=0){
            throw new BaseApiException(ResponseCodeEnum.NO_SUCH_USER);
        }
        message.setSenddate(new DateReform());
        message.setIsNotice(false);
        message.setIsReceived(false);
        return messageMapper.insert(message)>0;
    }

    @Override
    public Map<Long, List<Message>> getMessages(Long userId) {
        if(userId<=0){
            throw new BaseApiException(ResponseCodeEnum.NO_SUCH_USER);
        }
        userExample.clear();
        userExample.or().andUseridEqualTo(userId);
        long count=userMapper.countByExample(userExample);
        if (count<=0){
            throw new BaseApiException(ResponseCodeEnum.NO_SUCH_USER);
        }
        messageExample.clear();
        messageExample.or().andReceiveridEqualTo(userId);
        messageExample.or().andSenderidEqualTo(userId);
        messageExample.or().andIsNoticeEqualTo(true);
        messageExample.setOrderByClause("senddate");
        List<Message> messages=messageMapper.selectByExample(messageExample);
        if(messages==null||messages.isEmpty()){
            return null;
        }

        Map<Long, List<Message>> result=new LinkedHashMap<>();
        //
        bannedmsgExample.clear();
        bannedmsgExample.or().andBannedbyIdEqualTo(userId);
        List<Bannedmsg> bannedmsgList=bannedmsgMapper.selectByExample(bannedmsgExample);
        Map<Long, Null> bannedMap=new HashMap<>();
        for (Bannedmsg bs:bannedmsgList){
            if(!bannedMap.containsKey(bs.getBanneduserId())){
                bannedMap.put(bs.getBanneduserId(),null);
            }
        }
        for(Message m:messages){
            Long senderId=m.getSenderid();
            Long receiverId=m.getReceiverid();
            if (receiverId.equals(userId)) {
                if(!bannedMap.containsKey(senderId)&&result.containsKey(senderId)){
                    result.get(senderId).add(m);
                }else {
                    List<Message> messageList= new ArrayList<>();
                    if (!bannedMap.containsKey(senderId)) {
                        messageList.add(m);
                    }
                        result.put(senderId,messageList);

                }
            }else if (senderId.equals(userId)) {
                if(!bannedMap.containsKey(receiverId)&&result.containsKey(receiverId)){
                    result.get(receiverId).add(m);
                }else {
                    List<Message> messageList= new ArrayList<>();
                    if (!bannedMap.containsKey(receiverId)) {
                        messageList.add(m);
                    }
                        result.put(receiverId,messageList);

                }
            }
        }
        return result;
    }

    /*@Override
    public User getUser(Long userId) {
        if(userId<=0){
            throw new BaseApiException(ResponseCodeEnum.NO_SUCH_USER);
        }
        userExample.clear();
        userExample.or().andUseridEqualTo(userId);
        long count=userMapper.countByExample(userExample);
        if (count<=0){
            throw new BaseApiException(ResponseCodeEnum.NO_SUCH_USER);
        }
        messageExample.clear();
        messageExample.or().andReceiveridEqualTo(userId);
        messageExample.or().andSenderidEqualTo(userId);
        messageExample.or().andIsNoticeEqualTo(true);
        messageExample.setOrderByClause("senddate");
        List<Message> messages=messageMapper.selectByExample(messageExample);
        if(messages==null||messages.isEmpty()){
            return null;
        }
        bannedmsgExample.clear();
        bannedmsgExample.or().andBannedbyIdEqualTo(userId);
        List<Bannedmsg> bannedmsgList=bannedmsgMapper.selectByExample(bannedmsgExample);
        Map<Long, Null> bannedMap=new HashMap<>();
        for (Bannedmsg bs:bannedmsgList){
            if(!bannedMap.containsKey(bs.getBanneduserId())){
                bannedMap.put(bs.getBanneduserId(),null);
            }
        }
        List<User> userList=new ArrayList<>();
        userId
    }*/

    @Override
    public List<Message> getMessages(Long userId,Long senderId) {
        if(userId<=0){
            throw new BaseApiException(ResponseCodeEnum.NO_SUCH_USER);
        }
        userExample.clear();
        userExample.or().andUseridEqualTo(userId);
        long count=userMapper.countByExample(userExample);
        if (count<=0){
            throw new BaseApiException(ResponseCodeEnum.NO_SUCH_USER);
        }
        //
        bannedmsgExample.clear();
        bannedmsgExample.or().andBannedbyIdEqualTo(userId).andBanneduserIdEqualTo(senderId);
        List<Bannedmsg> bannedmsgList=bannedmsgMapper.selectByExample(bannedmsgExample);
        if(bannedmsgList!=null&&!bannedmsgList.isEmpty()){
            return null;
        }
        messageExample.clear();
        messageExample.or().andReceiveridEqualTo(userId).andSenderidEqualTo(senderId);
        messageExample.or().andSenderidEqualTo(userId).andReceiveridEqualTo(senderId);
        messageExample.or().andIsNoticeEqualTo(true);
        messageExample.setOrderByClause("senddate");
        List<Message> messages=messageMapper.selectByExample(messageExample);
        if(messages==null||messages.isEmpty()){
            return null;
        }
        messageExample.clear();
        messageExample.or().andReceiveridEqualTo(userId).andSenderidEqualTo(senderId);
        Message message=new Message();
        message.setIsReceived(true);
        messageMapper.updateByExampleSelective(message,messageExample);
        return messages;
    }

    @Override
    public boolean blockUser(Long blockerId,Long blockedId) {
        if(blockedId<=0||blockerId<=0){
            return false;
        }
        bannedmsgExample.clear();
        bannedmsgExample.or().andBannedbyIdEqualTo(blockerId).andBanneduserIdEqualTo(blockedId);
        if(bannedmsgMapper.countByExample(bannedmsgExample)>0){
            throw  new BaseApiException(ResponseCodeEnum.ALREADY_BLOCKED);
        }
        Bannedmsg bannedmsg=new Bannedmsg();
        bannedmsg.setBannedbyId(blockerId);
        bannedmsg.setBanneduserId(blockedId);
        return bannedmsgMapper.insert(bannedmsg)>0;
    }

    @Override
    public boolean unBlockUser(Long blockerId,Long blockedId) {
        if(blockedId<=0||blockerId<=0){
            return false;
        }
        bannedmsgExample.clear();
        bannedmsgExample.or().andBannedbyIdEqualTo(blockerId).andBanneduserIdEqualTo(blockedId);
        if(bannedmsgMapper.countByExample(bannedmsgExample)<=0){
            throw new BaseApiException(ResponseCodeEnum.NOT_BLOCKED);
        }
        bannedmsgExample.clear();
        bannedmsgExample.or().andBannedbyIdEqualTo(blockerId).andBanneduserIdEqualTo(blockedId);
        return bannedmsgMapper.deleteByExample(bannedmsgExample)>0;
    }

    @Override
    public boolean sendNotice(Message message) {
        message.setSenderid(0l);
        message.setIsNotice(true);
        message.setSenddate(new DateReform());
        message.setReceiverid(0l);
        message.setIsReceived(false);
        return messageMapper.insert(message)>0;
    }

    @Override
    public long countUnreadMessage(Long userId) {
        if(userId<=0){
            throw new BaseApiException(ResponseCodeEnum.NO_SUCH_USER);
        }
        userExample.clear();
        userExample.or().andUseridEqualTo(userId);
        long count=userMapper.countByExample(userExample);
        if (count<=0){
            throw new BaseApiException(ResponseCodeEnum.NO_SUCH_USER);
        }
        messageExample.clear();
        messageExample.or().andReceiveridEqualTo(userId).andIsReceivedEqualTo(false);
       return messageMapper.countByExample(messageExample);
    }
}
