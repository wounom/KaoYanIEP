package com.wounom.kaoyaniep.service;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.User;

import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/3/30 15:14
 */
public interface UserService {
    
    /**
     * 登录验证
     * 
     * @param user
     * @return 
     * @author litind
     **/
    User loginCheck(User user);

    /**
     * 用户注册
     *
     * @param user
     * @return void
     * @author litind
     **/
    Result register(User user);


    /**
     *
     * 邮件校验
     * @param email
     * @return java.lang.Boolean
     * @author litind
     **/
    boolean isUserEmailexsit(String email);

    /**
     *
     * 通过邮箱获取用户
     * @param email
     * @return com.wounom.kaoyaniep.entity.User
     * @author litind
     **/
    User getUserByEmail(String email);

    /**
     *
     * 忘记密码
     * @param user
     * @return java.lang.Boolean
     * @author litind
     **/
    Result forgetPw(User user);

    /**
     *
     * 修改密码
     * @param user
     * @return java.lang.Boolean
     * @author litind
     **/
    Result resetPw(User user,String newpassword);

    /**
     * 邮箱，用户名，生日，性别，目标院校
     * 修改个人信息
     * @return void
     * @author litind
     **/
    Result updateUserInfo(User user);


    Boolean isUsernameExsit(String username);



    int countAll(String search);

    List<User> getUsers(String search);


    User getUserById(Integer id);

    void deleteById(Integer id);

}
