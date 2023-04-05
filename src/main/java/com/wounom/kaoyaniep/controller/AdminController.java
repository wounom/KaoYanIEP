package com.wounom.kaoyaniep.controller;


import com.wounom.kaoyaniep.entity.Admin;
import com.wounom.kaoyaniep.entity.FirstpagePush;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/2 9:16
 */
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    /**
     *
     * 管理员登录
     * 账号，密码
     * @return com.wounom.kaoyaniep.entity.Result
     * @author litind
     **/
    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public Result login(HttpServletRequest request, Admin admin){
        if(!adminService.isAdminexsit(admin.getUsername())){
            return new Result(400,"账号不存在,或不是管理员账号");
        }
        Admin newadmin = adminService.getAdminByUsername(admin.getUsername());//查询已经注册的该邮箱账户
        if(adminService.loginCheck(admin,newadmin)){
            request.getSession().setAttribute("admin",newadmin);
            request.getSession().setMaxInactiveInterval(1800);
            return new Result(200,"登录成功");
        }else {
            return new Result(400,"用户名或密码错误");
        }
    }
    /**
     *
     * 运维增加管理员
     * 账号，密码
     * @param admin
     * @return
     * @author litind
     **/
    @PostMapping("/newAdmin")
    @ApiOperation("新建管理员")
    public Result newAdmin(Admin admin){
        if(adminService.isAdminexsit(admin.getUsername())){
            return new Result(400,"管理员账号已存在");
        }
        return adminService.addAdmin(admin);
    }

    /**
     *
     * 运维修改密码
     * 账号,新密码
     * @param admin
     * @return
     * @author litind
     **/
    @PostMapping("/resetadmin")
    @ApiOperation("修改管理员密码")
    public  Result updateAdmin(Admin admin){
        if(!adminService.isAdminexsit(admin.getUsername())){
            return new Result(400,"管理员账号不存在");
        }
        return adminService.updateAdmin(admin);
    }
    /**
     *
     * 上传首页推送
     * @param firstpagePush
     * @return
     * @author litind
     **/
    @PostMapping("/Fppush")
    @ApiOperation("上传首页推送")
    public Result updateFpagePush(FirstpagePush firstpagePush, MultipartFile file, HttpServletRequest request){
        if (file.isEmpty()){
            return new Result(400,"图片为空");
        }
        return adminService.updateFpp(firstpagePush,file,request);
    }
    /**
     *
     * 重置首页推送（删除）
     * @param
     * @return
     * @author litind
     **/
    @PostMapping("/deletepush")
    @ApiOperation("重置首页推送")
    public Result resetFpagePush(int first_Id){
        return adminService.deleteFpp(first_Id);
    }

}
