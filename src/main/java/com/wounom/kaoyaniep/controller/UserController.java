package com.wounom.kaoyaniep.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.wounom.kaoyaniep.dao.UserMapper;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.University;
import com.wounom.kaoyaniep.entity.User;
import com.wounom.kaoyaniep.service.UserService;
import com.wounom.kaoyaniep.utils.FileUtil;
import com.wounom.kaoyaniep.utils.TokenUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * @author litind
 * @version 1.0
 * @date 2023/3/30 14:38
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    /**
     *
     * 根据token获取用户信息
     * @param request
     * @return
     * @author litind
     **/
    @GetMapping("/getInfo")
    @ApiOperation("根据token获取用户信息")
    public Result test(HttpServletRequest request){
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
        return new Result(200,"UserInfo",1,user);
    }

    /**
     *
     * 注册
     * 用户名，邮箱，验证码，密码
     * @param user
     * @return com.wounom.kaoyaniep.entity.Result
     * @author litind
     **/
    @ApiOperation("注册")
    @PostMapping("/regist")
    @ResponseBody
    public Result Register(@RequestBody User user){
        if(userService.isUserEmailexsit(user.getEmail())){//查询已经注册的账户
            return new Result(400,"用户已存在",0,"");
        }
       return userService.register(user);
    }
    /**
     *
     * 登陆
     * @param user
     * @param user
     * @return com.wounom.kaoyaniep.entity.Result
     * @author litind
     **/
    @ApiOperation("登录")
    @PostMapping("/login")
    //@ResponseBody
    public Result Login(/*HttpServletRequest request,HttpServletResponse response,*/ @RequestBody User user) throws UnsupportedEncodingException {
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        /*log.info("username",user.getUsername());
        log.info("password",user.getPassword());*/
        if(!userService.isUserEmailexsit(user.getEmail())){
            return new Result(400,"账号不存在");
        }
        User newuser = userService.getUserByEmail(user.getEmail());//查询已经注册的该邮箱账户
        newuser.setCode(null);
        newuser.setActive_Time(null);
        newuser.setSalt(null);
        newuser.setPassword(null);
        newuser.setImagePath(null);
        Map<String,Object> map = new HashMap<>();
        map.put("user",newuser);
        if(userService.loginCheck(user)!=null){
            String token = TokenUtils.CreateToken(newuser);
            map.put("token",token);
           /*request.getSession().setAttribute("user",newuser);
            request.getSession().setMaxInactiveInterval(604800);
            String sessionId = request.getSession().getId();
            map.put("sessionId",sessionId);
            ResponseCookie cookie = ResponseCookie.from("JSESSIONID",request.getSession().getId())
                    .secure(true)

                    .httpOnly(true)
                    .sameSite("none")
                    .build();
            response.setHeader(HttpHeaders.SET_COOKIE,cookie.toString());*/
            //response.addCookie(new Cookie("JSESSIONID",request.getSession().getId()));
            /*response.setHeader("Access-Control-Allow-Origin", "http://172.25.88.146:8080");*/
            return new Result(200,"登录成功",map.size(),map);
        }else {
            return new Result(400,"用户名或密码错误");
        }
    }


    /**
     *
     * 登出
     * @param request,sessionStatus
     * @return com.wounom.kaoyaniep.entity.User
     * @author litind
     **/
    @ApiOperation("登出")
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        if(TokenUtils.removeToken(token)){
            return new Result(200,"登出成功");
        }
        else {
            return new Result(400,"登出失败,系统异常,请联系管理员");
        }
    }


    /**
     * 邮箱，验证码，新密码
     * 忘记密码
     * @param user
     * @return com.wounom.kaoyaniep.entity.User
     * @author litind
     **/
    @ApiOperation("忘记密码")
    @PutMapping("/forgetpw")
    public Result forgetpw(@RequestBody User user){
        if(!userService.isUserEmailexsit(user.getEmail())){
            return new Result(400,"账号不存在");
        }
        return userService.forgetPw(user);
    }

    /**
     *
     * 修改密码
     * 邮箱，原密码，新密码
     * @param map
     * @return com.wounom.kaoyaniep.entity.User
     * @author litind
     **/
    @ApiOperation("修改密码(String oldPwd,String newPwd)")
    @PutMapping("/resetpw")
    public Result resetPw(HttpServletRequest request,@RequestBody Map<String,String> map){

        String oldPwd=map.get("oldPwd");
        String newpassword = map.get("newPwd");
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
        user.setPassword(oldPwd);
        return userService.resetPw(user,newpassword);
    }

    @ApiOperation("查询所有用户：用作测试")
    @GetMapping("/findall")
    public List<User> findall(){
        return userMapper.findall();
    }

    /**
     *
     * 更新用户信息
     * @param user
     * @return
     * @author litind
     **/
    @ApiOperation("更新用户信息")
    @PutMapping("/updateUserInfo")
    public Result updateUserinfo(@RequestBody User user,HttpServletRequest request){//user不用传入Email，通过session获取
        String token = request.getHeader("token");
        User oldUser = TokenUtils.getUser(token);
        user.setEmail(oldUser.getEmail());
        return userService.updateUserInfo(user);
    }

    /**
     *
     * 上传用户头像
     * @param file,request,
     * @return
     * @author litind
     **/
    @PostMapping("/uploadimage")
    @ApiOperation("上传用户头像")
    public Result uploadImg(MultipartFile file,HttpServletRequest request){
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
        String email = user.getEmail();
        if (file.isEmpty()){
            return new Result(400,"文件为空");
        }
        return userService.uploadimg(email,file,request);
    }



}
