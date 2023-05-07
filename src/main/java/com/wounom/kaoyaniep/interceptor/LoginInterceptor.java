package com.wounom.kaoyaniep.interceptor;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.User;
import com.wounom.kaoyaniep.utils.TokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author litind
 * @version 1.0
 * @date 2023/3/30 14:38
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

          /*if (request.getSession().getAttribute("user")!=null){
            return true;
        }
        Result result = new Result(400,"session无效");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(result);
        return false;*/
          if ("OPTIONS".equals(request.getMethod())) {//这里通过判断请求的方法，判断此次是否是预检请求，如果是，立即返回一个204状态吗，标示，允许跨域；预检后，正式请求，这个方法参数就是我们设置的post了
              response.setStatus(HttpStatus.NO_CONTENT.value()); //HttpStatus.SC_NO_CONTENT = 204
              response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, DELETE");//当判定为预检请求后，设定允许请求的方法
              response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, Token"); //当判定为预检请求后，设定允许请求的头部类型
              response.addHeader("Access-Control-Max-Age", "1");
              return true;
          }
        //token验证
        String token = request.getHeader("token");
        if (ObjectUtils.isEmpty(token)){
            Result result = new Result("400","token为空");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(result.toString());
            response.getWriter().flush();
            response.getWriter().close();
            return false;
        }
        if (TokenUtils.verfiry(token)){//验证token
            return true;
        }else {
            Result result = new Result("400","登录超时或无效token");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(result.toString());
            response.getWriter().flush();
            response.getWriter().close();
            return false;
        }
    }
    /*@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }*/
}
