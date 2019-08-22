package com.wxm.bbsdemo.controller;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.wxm.bbsdemo.entity.ResponseData;
import com.wxm.bbsdemo.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyController {
    @Autowired
    VerifyService verifyService;
    @RequestMapping("/api/verifyToken")
    public ResponseData verifyToken(@RequestParam(value = "token") String token){
        boolean a=verifyService.verify(token);
        if(a){
            System.out.println("认证成功，token:"+token);
        }else{
            System.out.println("认证失败，token:"+token);
        }
        return new ResponseData(verifyService.verify(token));
    }
}
