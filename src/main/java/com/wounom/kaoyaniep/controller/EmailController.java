package com.wounom.kaoyaniep.controller;



import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.User;
import com.wounom.kaoyaniep.service.EmailService;
//import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.awt.geom.RectangularShape;
import java.util.Map;


/**
 * @author  litind
 * @date    2023/3/30 14:39
 * @version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/email")
public class EmailController {

    @Resource
    private EmailService emailService;
    /**
     *
     * 发送注册验证码
     * @param email
     * @return
     * @author litind
     **/
    @PostMapping("/sendemailRegist")
    @ApiOperation("发送注册邮件")
    public Result sendRegist(@RequestParam(value = "email")String email){
        JSONObject jsonObject = JSON.parseObject(email);
        email = jsonObject.getString("email");
        return emailService.sendEmail(email,1);
    }

    /**
     *
     * 发送忘记密码邮件
     * @param email
     * @return
     * @author litind
     **/
    @PostMapping("/sendforget")
    @ApiOperation("发送忘记密码邮件")
    @ResponseBody
    public Result sendForget(@RequestParam(value = "email")String email){
        /*JSONObject jsonObject = JSON.parseObject(email);
        email = jsonObject.getString("email");*/
        return emailService.sendEmail(email,0);
    }

}
