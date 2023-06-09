package com.wounom.kaoyaniep.service.impl;

import com.wounom.kaoyaniep.dao.AttentionMapper;
import com.wounom.kaoyaniep.dao.CommentMapper;
import com.wounom.kaoyaniep.dao.TieWenMapper;
import com.wounom.kaoyaniep.entity.*;
import com.wounom.kaoyaniep.dao.UserMapper;
import com.wounom.kaoyaniep.service.UserService;
import com.wounom.kaoyaniep.utils.FileUtil;
import com.wounom.kaoyaniep.utils.PasswordUtil;
import com.wounom.kaoyaniep.utils.TokenUtils;
import com.wounom.kaoyaniep.utils.UserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author litind
 * @version 1.0
 * @date 2023/3/30 15:15
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    private AttentionMapper attentionMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private TieWenMapper tieWenMapper;
    @Override
    public boolean isUserEmailexsit(String email){
        return userMapper.selectByUserEmail1(email) != null;
    }


    @Override
    public User loginCheck( User user) {
        User tmpUser = userMapper.selectByUserEmail1(user.getEmail());//激活过的user
        String lpw = PasswordUtil.md5Pwd(user.getPassword(),tmpUser.getSalt());//将传入的user密码进行加密
        if(lpw.equals(tmpUser.getPassword())){
            return tmpUser;
        }else{
            return null;
        }


    }

    /**
     *
     * 注册
     * @param user
     * @return com.wounom.kaoyaniep.entity.Result
     * @author litind
     **/
    @Override
    public Result register(User user) {
        user.setSalt(PasswordUtil.createSalt());//设置用户加密盐
        user.setPassword(PasswordUtil.md5Pwd(user.getPassword(),user.getSalt()));
        user.setIsValid(1);
        String name =UserUtils.getRandomChineseString();
        user.setUsername(name);
        System.out.println(name);
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
        user.setActiveTime(LocalDateTime.now());
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
            user.setActiveTime(LocalDateTime.now());
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
    public Result updateUserInfo(User user,HttpServletRequest request){
        int r = userMapper.updateUser(user);
        User newuser = userMapper.selectByUserEmail1(user.getEmail());
        if (user.getUsername()!=null){//更新其他表中的用户名comment,tiewen
            commentMapper.updateUsername(newuser);
            tieWenMapper.updateUsername(newuser);
        }
        TokenUtils.updateTokenmap(newuser,request);
        if(r>0){
            return new Result(200,"修改成功");
        }else {
            return new Result(400,"修改失败");
        }
    }

    /**
     *
     * 上传头像
     * @param eamil.file
     * @return java.lang.Boolean
     * @author litind
     **/
    @Value("${file.upload-path}")
    private String imgPath;
    @Value("${file.upload-ip}")
    private String ip;
    @Override
    public Result uploadimg(String email, MultipartFile file, HttpServletRequest request){
        User user = userMapper.selectByUserEmail1(email);
        if (user.getImagepath()!=null){
            FileUtil.deleteFile(user.getImagepath());
        }
        try {
            String newFn = FileUtil.saveFile(file,imgPath);
            String url = request.getScheme()+"://"+ip+":"+request.getServerPort() +"/images/userheadimg/"+newFn;//todo:部署后记得改ip
            String path = imgPath+newFn;
            User newuser = new User();
            newuser.setEmail(email);
            newuser.setImage(url);
            newuser.setImagepath(path);
            userMapper.updateUserImg(newuser);
            user.setImage(url);
            user.setImagepath(path);
            commentMapper.updateImg(user);
            TokenUtils.updateTokenmap(user,request);
            return new Result(200,url);
        } catch (IOException e) {

            return new Result(400,"上传失败"+e.getMessage());
        }

    }








}
