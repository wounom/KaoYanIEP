package com.wounom.kaoyaniep.service;

import com.wounom.kaoyaniep.entity.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author litind
 * @version 1.0
 * @date 2023/3/30 17:30
 */

public interface EmailService {
    /**
     * 注册邮件发送
     *
     * @param email
     * @return
     * @author litind
     **/
    Result sendEmail(String email,int way);
}
