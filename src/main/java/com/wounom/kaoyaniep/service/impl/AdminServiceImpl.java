package com.wounom.kaoyaniep.service.impl;

import com.wounom.kaoyaniep.dao.AdminMapper;
import com.wounom.kaoyaniep.entity.Admin;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.service.AdminService;
import com.wounom.kaoyaniep.utils.PasswordUtil;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.geom.RectangularShape;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/2 9:34
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Override
    public boolean isAdminexsit(String username){
        return adminMapper.selectAdminByUsername(username) != null;
    }

    @Override
    public Admin getAdminByUsername(String username){
        return adminMapper.selectAdminByUsername(username);
    }

    @Override
    public Boolean loginCheck(Admin admin,Admin newadmin){
        String cpw = PasswordUtil.md5Pwd(admin.getPassword(),newadmin.getSalt());
        if (cpw.equals(newadmin.getPassword())){
            return true;
        }else {
            return false;
        }
    }
    /**
     *
     * 增加管理员
     * @param admin
     * @return
     * @author litind
     **/
    @Override
    public Result addAdmin(Admin admin){
        admin.setSalt(PasswordUtil.createSalt());
        admin.setPassword(PasswordUtil.md5Pwd(admin.getPassword(),admin.getSalt()));
        int r = adminMapper.insertAdmin(admin);
        if(r>0){
            return new Result(200,"增加成功");
        }else {
            return new Result(400,"增加失败");
        }
    }
    /**
     *
     * 修改管理员密码
     * @param admin
     * @return
     * @author litind
     **/
    @Override
    public Result updateAdmin(Admin admin){
        admin.setSalt(PasswordUtil.createSalt());
        admin.setPassword(PasswordUtil.md5Pwd(admin.getPassword(),admin.getSalt()));
        int r = adminMapper.updateAdmin(admin);
        if (r>0){
            return new Result(200,"修改成功");
        }else {
            return new Result(400,"修改失败");
        }
    }
}
