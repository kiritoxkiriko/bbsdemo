package com.wxm.bbsdemo.interceptor;

import com.wxm.bbsdemo.annotation.LoginRequire;
import com.wxm.bbsdemo.exception.BaseApiException;
import com.wxm.bbsdemo.exception.ResponseCodeEnum;
import com.wxm.bbsdemo.service.VerifyService;
import com.wxm.bbsdemo.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    VerifyService verifyService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)||!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        //if (method.isAnnotationPresent(PassToken.class)) {
        //    PassToken passToken = method.getAnnotation(PassToken.class);
        //    if (passToken.required()) {
        //        return true;
        //    }
        //}
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(LoginRequire.class)) {
            LoginRequire loginRequire = method.getAnnotation(LoginRequire.class);
            if (loginRequire.required()) {
                // 执行认证
                if (token == null) {
                    throw new BaseApiException(ResponseCodeEnum.NO_TOKEN);
                }
                if(verifyService.verify(token)){
                    
                }else {
                    return false;
                }

            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
