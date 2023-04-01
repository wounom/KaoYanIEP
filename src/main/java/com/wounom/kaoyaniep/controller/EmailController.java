package com.wounom.kaoyaniep.controller;


import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.service.EmailService;
//import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.awt.geom.RectangularShape;


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
    public Result sendRegist(String email){
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
    public Result sendForget(String email){
        return emailService.sendEmail(email,0);
    }

}
