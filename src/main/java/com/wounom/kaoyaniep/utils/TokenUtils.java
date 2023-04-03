package com.wounom.kaoyaniep.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wounom.kaoyaniep.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/3 12:18
 */
@Slf4j
public class TokenUtils {
    private static final Long EXPIRE_TIME = 10*60*60*1000L;//过期时间为10小时
    private static final String TOKEN_SECRET =
            "elkq2ndfg46pqenrkl56LFAEFdf2jaoengn.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4g";

    //生成 Token
    public static String CreateToken(User user){

        String token = null;

        try {
            Date expire = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("litind")  //发行人
                    .withClaim("email",user.getEmail()) //存放数据
                    .withExpiresAt(expire) //过期时间
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));//加密方式
        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }
/*
    // TOKEN 验证
    public static Boolean verfiry(String token){

        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET))
                    .withIssuer("litind")
                    .build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            log.info("TOKEN 验证通过");
            log.info("username :"+ decodedJWT.getClaim("username").asString());
            log.info("过期时间："+ decodedJWT.getExpiresAt());
        }catch (Exception e){
            // 抛出错误即为验证不通过
            log.error("TOKEN 验证不通过,请再次输入");
            return false;
        }
        return true;
    }*/
}
