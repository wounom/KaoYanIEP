package com.wounom.kaoyaniep.interceptor;

import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wounom.kaoyaniep.entity.Result;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.security.pkcs11.wrapper.CK_SESSION_INFO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author litind
 * @version 1.0
 * @date 2023/3/30 14:38
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        if (request.getSession().getAttribute("user")!=null){
            return true;
        }
        Result result = new Result(400,"session无效");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(result);
        return false;
        //token验证
        /*String token = request.getHeader("token");
        if (ObjectUtils.isEmpty(token)){
            Result result = new Result("400","登录超时或无效token");
            response.getWriter().write(result.toString());
            response.getWriter().flush();
            response.getWriter().close();
            return false;
        }
        DecodedJWT decodedJWT = JWT.decode(token);
        String email = decodedJWT.getClaim("email").asString();
        if (email == null){
            Result result = new Result("400","登录超时或无效token");
            response.getWriter().write(result.toString());
            response.getWriter().flush();
            response.getWriter().close();
            return false;
        }
        return true;*/
    }
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
}
