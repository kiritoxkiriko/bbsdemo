package com.wxm.bbsdemo.service;

import com.wxm.bbsdemo.entity.User;


import java.util.List;

public interface UserService {
    public List<User>find(User user);

    public User getUserByUserId(Long userId);

    public boolean insert(User user);

    public boolean activate(Long userId);

    public boolean delectByUserId(Long userId);

    public boolean updateByUserId(User user);

    public Long verfiyPassword(String userName,String password);

    public boolean verfiyPassword(Long userId,String password);

    public long countUser();

    public boolean exist(String username);

    public boolean exist(Long userId);

    public int exist(String username,String mail,String nickname);

}
