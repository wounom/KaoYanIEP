package com.wounom.kaoyaniep.service.impl;

import com.wounom.kaoyaniep.dao.UserMapper;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.User;
import com.wounom.kaoyaniep.service.EmailService;
import com.wounom.kaoyaniep.utils.EmailUtil;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/3/30 17:30
 */
@Service
public class EmailServiceImpl implements EmailService {
    @Value("${spring.mail.username}")
    private String mailUsername;
    @Value("${spring.mail.password}")
    private String mailpassword;

    @Value("${spring.mail.host}")
    private String mailhost;
    @Value("${spring.mail.default-encoding}")
    private String mailencode;

    @Resource
    private TemplateEngine templateEngine;
    @Resource
    private UserMapper userMapper;
    @Resource
    private EmailUtil emailUtil;
    /**
     *
     * 激活邮件
     * @param email
     * @return
     * @author litind
     **/
    public Result sendEmail(String email,int way){
        User userfindByemail = userMapper.selectUserByEmail_(email);
        HtmlEmail sendEmail = new HtmlEmail();
        String code = emailUtil.CreateCode();
        try{
            sendEmail.setHostName(mailhost);
            sendEmail.setCharset(mailencode);
            sendEmail.addTo(email);//注入需要发送的邮箱
            sendEmail.setFrom(mailUsername,"考研信息发布及交流平台");
            sendEmail.setAuthentication(mailUsername,mailpassword);
            sendEmail.setSubject("考研信息发布及交流平台");
            Date date = new Date();
            sendEmail.setSentDate(date);
            Context context = new Context();
            context.setVariable("email",email);
            context.setVariable("code",code);
            String text;
            if(way ==1){
                text = templateEngine.process("active-account.html",context);
            }else {
                text = templateEngine.process("resetpassword.html",context);
            }
            sendEmail.setContent(text,"text/html");
            sendEmail.send();
            LocalDateTime activeTime = LocalDateTime.now().plusMinutes(5);//验证码的存活时间
            if( userfindByemail==null){
                User user = new User();
                user.setEmail(email);
                user.setCode(code);
                user.setActive_Time(activeTime);
                userMapper.insertCode(user);
            }else {
                User user = new User();
                user.setEmail(email);
                user.setCode(code);
                user.setActive_Time(activeTime);
                userMapper.updateUsercode(user);//新增code项，后续用户信息插入code
            }
            return new Result(200,"发送成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(400,"发送异常");
        }

    }
}
