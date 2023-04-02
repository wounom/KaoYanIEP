package com.wounom.kaoyaniep.service.impl;

import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.User;
import com.wounom.kaoyaniep.dao.UserMapper;
import com.wounom.kaoyaniep.service.UserService;
import com.wounom.kaoyaniep.utils.PasswordUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/3/30 15:15
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;


    @Override
    public boolean isUserEmailexsit(String email){
        return userMapper.selectByUserEmail1(email) != null;
    }


    @Override
    public User loginCheck(User user) {
        User tmpUser = userMapper.selectByUserEmail1(user.getEmail());//激活过的user
        String lpw = PasswordUtil.md5Pwd(user.getPassword(),tmpUser.getSalt());//将传入的user密码进行加密
        if(lpw.equals(tmpUser.getPassword())){
            return tmpUser;
        }else{
            return null;
        }


    }

    @Override
    public Result register(User user) {
        user.setSalt(PasswordUtil.createSalt());//设置用户加密盐
        user.setPassword(PasswordUtil.md5Pwd(user.getPassword(),user.getSalt()));
        user.setIs_Valid(1);
        user.setUsername(user.getUsername());
        String ocode = user.getCode();
        String ccode = userMapper.findCode(user.getEmail());
        if(!ccode.equals(ocode)){
            return new Result(400,"验证码错误");
        }
        if(LocalDateTime.now().isAfter(userMapper.findActive(user.getEmail()))){
            return new Result(400,"验证码超时");
        }
        userMapper.updateregist(user);
        return new Result(200,"注册成功");
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.selectByUserEmail1(email);
    }

    /**
     *
     * 忘记密码
     * @param user
     * @return java.lang.Boolean
     * @author litind
     **/
    @Override
    public Result forgetPw(User user){
        user.setSalt(PasswordUtil.createSalt());//设置用户加密盐
        user.setPassword(PasswordUtil.md5Pwd(user.getPassword(),user.getSalt()));
        String ocode = user.getCode();
        String ccode = userMapper.findCode(user.getEmail());
        if(!ccode.equals(ocode)){
            return new Result(400,"验证码错误");
        }
        if(LocalDateTime.now().isAfter(userMapper.findActive(user.getEmail()))){
            return new Result(400,"验证码超时");
        }
        user.setCode("");
        user.setActive_Time(LocalDateTime.now());
        userMapper.updatePw(user);
        return new Result(200,"密码修改成功");
    }

    /**
     *
     * 修改密码
     * @param user
     * @return java.lang.Boolean
     * @author litind
     **/
    public Result resetPw(User user,String newpassword){

        User tmpUser = userMapper.selectByUserEmail1(user.getEmail());//激活过的user
        String lpw = PasswordUtil.md5Pwd(user.getPassword(),tmpUser.getSalt());//将传入的user密码进行加密
        if(lpw.equals(tmpUser.getPassword())){
            user.setSalt(PasswordUtil.createSalt());//设置用户加密盐
            user.setPassword(PasswordUtil.md5Pwd(newpassword,user.getSalt()));
            user.setActive_Time(LocalDateTime.now());
            user.setCode("");
            userMapper.updatePw(user);
            return new Result(200,"密码修改成功");
        }
        return new Result(400,"密码修改失败，请检查原密码是否正确");
    }

    /**
     *
     * 更新用户信息
     * @param user
     * @return java.lang.Boolean
     * @author litind
     **/
    @Override
    public Result updateUserInfo(User user){
        int r = userMapper.updateUser(user);
        if(r>0){
            return new Result(200,"修改成功");
        }else {
            return new Result(400,"修改失败");
        }
    }


    @Override
    public Boolean isUsernameExsit(String username) {
        return userMapper.selectByUsername(username) != null;
    }



    @Override
    public int countAll(String search) {
        return userMapper.countAll(search);
    }

    @Override
    public List<User> getUsers(String search) {
        return userMapper.getUsers(search);
    }


    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }
}
