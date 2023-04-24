package com.wounom.kaoyaniep.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wounom.kaoyaniep.entity.User;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/3 12:18
 */
@Slf4j
public class TokenUtils {


    private static Map<String,User> tokenMap = new HashMap<>();
    private static final Long EXPIRE_TIME = 7*24*60*60*1000L;//过期时间为7天
    private static final String TOKEN_SECRET =
            "litind@kaoyanircp.top-from:wounom.com";

    //生成 Token
    public static String CreateToken(User user){
        String token = null;
        try {
            Date expire = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("litind")  //发行人
                    .withClaim("userId", user.getId()) //存放数据
                    .withExpiresAt(expire) //过期时间
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));//加密方式
        }catch (Exception e){
            e.printStackTrace();
        }
        tokenMap.put(token,user);
        return token;
    }

    // TOKEN 验证
    public static Boolean verfiry(String token,HttpServletResponse response){
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET))
                    .withIssuer("litind")
                    .build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            log.info("TOKEN 验证通过");
            log.info("userId :"+ decodedJWT.getClaim("userId"));
            log.info("过期时间："+ decodedJWT.getExpiresAt());
            System.out.println(decodedJWT.getClaim("userId"));
        }catch (Exception e){
            // 抛出错误即为验证不通过
            log.error("TOKEN 验证不通过,请再次输入");
            return false;
        }
        return true;
    }

    // 通过token获取用户
    public static User getUser(String token){
       /* JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET))
                .withIssuer("litind")
                .build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        int id = decodedJWT.getClaim("userId").asInt();*/
        User user = tokenMap.get(token);
        System.out.println(user.toString());
        return user;
    }






   /*final static String SECRET = "litind@jwtToken";//私钥
    final static Gson gson = new Gson();
    final static long TOKEN_EXP = 60 * 60 * 24 * 7;//过期时间 七天
//    final static long TOKEN_EXP = 240 ;//过期时间,测试使用

    public static String CreateToken(User user) throws UnsupportedEncodingException {
        Algorithm al = Algorithm.HMAC256(SECRET);
        Instant instant = LocalDateTime.now().plusSeconds(TOKEN_EXP).atZone(ZoneId.systemDefault()).toInstant();
        Date expire = Date.from(instant);
        Gson gson = new Gson();
        String s = gson.toJson(user);
        String token = JWT.create()
                .withSubject("userInfo")
                .withClaim("user", s)//存入user
                .withExpiresAt(expire)
                .sign(al);
        return token;
    }

    *//**
     * @Param: 传入token
     * @return:
     *//*
    public static boolean verify(String token) throws UnsupportedEncodingException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            if (jwt.getExpiresAt().before(new Date())) {
                System.out.println("token已过期");
                return false;
            }
        } catch (Exception e) {
            System.out.println("token验证失败");
            return false;
        }
        return true;
    }

    *//**
     * 获取用户信息
     *
     * @param request
     * @return
     *//*
    public static User getUserIdByToken(HttpServletRequest request) throws UnsupportedEncodingException {
        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        Claim claim = jwt.getClaim("user");
        String json = claim.asString();
        User tbLoginUser = gson.fromJson(json, User.class);
        return tbLoginUser;
    }*/


}
