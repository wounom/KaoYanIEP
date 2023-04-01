package com.wounom.kaoyaniep.utils;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import org.springframework.stereotype.Service;

/**
 * @author litind
 * @version 1.0
 * @date 2023/3/30 16:04
 */

public class PasswordUtil {
    public static String md5Pwd(String password,String salt) {
        return SecureUtil.md5(password+salt);
    }

    public static String createSalt(){
        return RandomUtil.randomString(6);
    }
}
