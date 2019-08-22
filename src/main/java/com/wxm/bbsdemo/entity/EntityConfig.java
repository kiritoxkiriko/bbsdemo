package com.wxm.bbsdemo.entity;

import com.wxm.bbsdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntityConfig {

    @Bean
    public User user(){return new User();}

    @Bean
    public UserExample userExample(){return new UserExample();}

    @Bean
    public Banned banned(){return new Banned();}

    @Bean
    public BannedExample bannedExample(){return new BannedExample();}

    @Bean
    public Bannedmsg bannedmsg(){return new Bannedmsg();}

    @Bean
    public BannedmsgExample bannedmsgExample(){return new BannedmsgExample();}

    @Bean
    public Board board(){return new Board();}

    @Bean
    public BoardExample boardExample(){return new BoardExample();}

    @Bean
    public Message message(){return new Message();}

    @Bean
    public MessageExample messageExample(){return new MessageExample();}

    @Bean
    public Post post(){return new Post();}

    @Bean
    public PostExample postExample(){return new PostExample();}

    @Bean
    public Postreply postreply(){return new Postreply();}

    @Bean
    public PostreplyExample postreplyExample(){return new PostreplyExample();}

    @Bean
    public PostreplyComment postreplyComment(){return new PostreplyComment();}

    @Bean
    public PostreplyCommentExample postreplyCommentExample(){return new PostreplyCommentExample();}

    @Bean
    public Posttheme posttheme(){return new Posttheme();}

    @Bean
    public PostthemeExample postthemeExample(){return new PostthemeExample();}

}
