package com.wxm.bbsdemo;

import com.wxm.bbsdemo.entity.User;
import com.wxm.bbsdemo.service.MailService;
import com.wxm.bbsdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BbsdemoApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    //User user;
    //@Autowired
    //UserExample userExample;
    @Test
    public void contextLoads() {
//        mailService.send("645466558@qq.com","123","123145");
//            //long a=1;
//            //userExample.or().andUseridEqualTo(a);
//            //List<User> user= userService;
//
//            //System.out.println(user);


    }

}

