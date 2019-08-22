package com.wxm.bbsdemo.utility;





import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.omg.CORBA.portable.InputStream;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Scanner;

//token工具
public class TokenUtil {


    private static final String SECRET_CODE ="36d11172-b6ea-42e7-95b7-005d3a93147b";

    private static Algorithm algorithm;

    //对userId签名生成token
    static {
        try {
            algorithm=Algorithm.HMAC256(SECRET_CODE);//以sha256算法加密
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String sign(Long userId,long expireTime) {
        Date expiredDate = new Date(System.currentTimeMillis() + expireTime);
        return JWT.create().withClaim("userid", userId).withExpiresAt(expiredDate).sign(algorithm);
    }
    //验证token
    public static boolean verify(String token) throws TokenExpiredException{
        try {
            JWTVerifier verifier=JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        }catch (TokenExpiredException e) {
            throw e;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    //获取token中的userid,先验证
    public static Long getUserId(String token){
        DecodedJWT decodedJWT=JWT.decode(token);
        return decodedJWT.getClaim("userid").asLong();
    }
}
