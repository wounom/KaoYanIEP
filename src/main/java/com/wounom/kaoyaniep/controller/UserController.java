package com.wounom.kaoyaniep.controller;

import cn.hutool.core.map.MapUtil;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
    @ResponseBody
    public Result Login(HttpServletRequest request, @RequestBody User user){
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        /*log.info("username",user.getUsername());
        log.info("password",user.getPassword());*/
        if(!userService.isUserEmailexsit(user.getEmail())){
            return new Result(400,"账号不存在");
        }
        User newuser = userService.getUserByEmail(user.getEmail());//查询已经注册的该邮箱账户
        if(userService.loginCheck(user)!=null){
            String token = TokenUtils.CreateToken(newuser);
            request.getSession().setAttribute("user",newuser);
            request.getSession().setMaxInactiveInterval(1800);
            return new Result(200,"登录成功");
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
    public Result logout(HttpServletRequest request, SessionStatus sessionStatus) {
        request.getSession().invalidate();
        sessionStatus.setComplete();
        return new Result(200,"登出成功");
    }
    /**
     * 邮箱，验证码，新密码
     * 忘记密码
     * @param user
     * @return com.wounom.kaoyaniep.entity.User
     * @author litind
     **/
    @ApiOperation("忘记密码")
    @PostMapping("/forgetpw")
    public Result forgetpw(@RequestBody User user){
        if(!userService.isUserEmailexsit(user.getEmail())){
            return new Result(400,"账号不存在");
        }
        userService.forgetPw(user);
        return new Result(200,"修改成功");
    }

    /**
     *
     * 修改密码
     * 邮箱，原密码，新密码
     * @param param
     * @return com.wounom.kaoyaniep.entity.User
     * @author litind
     **/
    @ApiOperation("修改密码(String oldPwd,String newPwd)")
    @PostMapping("/resetpw")
    public Result resetPw(HttpServletRequest request,@RequestBody String param){
        JSONObject json = JSON.parseObject(param);
        String oldPwd=json.getString("oldPwd");
        String newpassword = json.getString("newPwd");
        User user = (User) request.getSession().getAttribute("user");
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
    @PostMapping("/updateUserInfo")
    public Result updateUserinfo(@RequestBody User user,HttpServletRequest request){//user不用传入Email，通过session获取
        User oldUser = (User) request.getSession().getAttribute("user");
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
        User user = (User) request.getSession().getAttribute("user");
        String email = user.getEmail();
        if (file.isEmpty()){
            return new Result(400,"文件为空");
        }
        return userService.uploadimg(email,file,request);
    }



}
