package com.wxm.bbsdemo.service.impl;

import com.wxm.bbsdemo.entity.User;
import com.wxm.bbsdemo.entity.UserExample;
import com.wxm.bbsdemo.exception.BaseApiException;
import com.wxm.bbsdemo.exception.ResponseCodeEnum;
import com.wxm.bbsdemo.mapper.UserMapper;
import com.wxm.bbsdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserExample userExample;

    @Override
    public List<User> find(User user) {
        boolean flag=false;
        List<User> userList=new ArrayList<User>();
        Long userId=user.getUserid();
        String nickName=user.getNickname();
        String userName=user.getUsername();
        String mail=user.getMail();
        String type=user.getType();
        userExample.clear();
        if(userId!=null){
            User tempUser=userMapper.selectByPrimaryKey(userId);
            if(tempUser!=null){
                if(tempUser.getIsPrivate()){
                    tempUser.setInfo(null);
                }
                userList.add(tempUser);
            }else {
                throw new BaseApiException(ResponseCodeEnum.NO_SUCH_DATA);
            }
            return userList;
        }else if(userName!=null){
            userExample.or().andUsernameEqualTo(userName);
        }else if(nickName!=null){
            userExample.or().andUsernameEqualTo(nickName);
        }else if(mail!=null){
            userExample.or().andMailEqualTo(mail);
        }else if (type!=null){
            userExample.or().andTypeEqualTo(type);
        }else {
            throw new BaseApiException(ResponseCodeEnum.WRONG_REQUEST_PARAMETER);
        }
        userList= userMapper.selectByExample(userExample);
        if(userList.isEmpty()||userList==null){
            return null;
        }
        return  userList;
    }

    @Override
    public User getUserByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public boolean insert(User user) {
        user.setIsPrivate(false);
        user.setPoint(0l);
        user.setType("unactivated");
        user.setLevel(1);
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return userMapper.insert(user)>0;
    }

    @Override
    public boolean activate(Long userId) {
        User user=new User();
        user.setUserid(userId);
        user.setType("normal");
        return userMapper.updateByPrimaryKeySelective(user)>0;
    }

    @Override
    public boolean delectByUserId(Long userId) {
        return userMapper.deleteByPrimaryKey(userId)>0;
    }

    @Override
    public boolean updateByUserId(User user) {
        return userMapper.updateByPrimaryKeySelective(user)>0;
    }

    @Override
    public Long verfiyPassword(String userName, String password) {
        User user=userMapper.selectPasswordByUsername(userName);
        if(user==null){
            return null;
        }else {
            //System.out.println(user);
            if(DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
                return user.getUserid();
            }else {
                throw new BaseApiException(ResponseCodeEnum.WRONG_PASSWORD);
            }
        }
    }

    @Override
    public boolean verfiyPassword(Long userId, String password) {
        User user=userMapper.selectPasswordByPrimaryKey(userId);
        if(user==null){
            return false;
        }else {
            if(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).equals(password)){
                return true;
            }else {
                return false;
            }
        }
    }

    @Override
    public long countUser() {
        userExample.clear();
        userExample.or().andUseridGreaterThan(0L);
        return userMapper.countByExample(userExample);
    }

    @Override
    public boolean exist(String username) {
        if(username==null||username.equals("")){
            return true;
        }
        userExample.clear();
        userExample.or().andUsernameEqualTo(username);
        return userMapper.countByExample(userExample)>0;
    }

    @Override
    public boolean exist(Long userId) {
        userExample.clear();
        userExample.or().andUseridEqualTo(userId);
        return userMapper.countByExample(userExample)>0;
    }

    @Override
    public int exist(String username, String mail, String nickname) {
        int result =0;
        userExample.clear();
        userExample.or().andMailEqualTo(mail);
        boolean isMail=userMapper.countByExample(userExample)>0;
        userExample.clear();
        userExample.or().andNicknameEqualTo(nickname);
        boolean isNickname=userMapper.countByExample(userExample)>0;
        if(exist(username)){
            result=1;
        }else if(isNickname){
            result=2;
        }else if(isMail){
            result=3;
        }else{
            result=0;
        }
        return result;

    }
}

