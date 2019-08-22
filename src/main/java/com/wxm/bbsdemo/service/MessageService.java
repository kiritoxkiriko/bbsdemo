package com.wxm.bbsdemo.service;

import com.wxm.bbsdemo.entity.Message;
import com.wxm.bbsdemo.entity.User;

import java.util.List;
import java.util.Map;

public interface MessageService {
    public boolean sendMessage(Message message);

    public boolean sendMessageBySystem(Message message);

    public Map<Long,List<Message>> getMessages(Long userId);

    //public User getUser(Long userId);

    public List<Message> getMessages(Long userId,Long senderId);

    public boolean blockUser(Long blockerId,Long blockedId);

    public boolean unBlockUser(Long blockerId,Long blockedId);

    public boolean sendNotice(Message message);

    public long countUnreadMessage(Long userId);
}
