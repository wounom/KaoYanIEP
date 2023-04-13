package com.wounom.kaoyaniep.service.impl;

import com.wounom.kaoyaniep.dao.AttentionMapper;
import com.wounom.kaoyaniep.entity.Attentionlist;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.University;
import com.wounom.kaoyaniep.entity.User;
import com.wounom.kaoyaniep.service.AttentionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/13 10:35
 */
@Service
public class AttentionServiceImpl implements AttentionService {
    @Resource
    private AttentionMapper attentionMapper;
    /**
     *
     * 关注用户或者院校
     * @param user,university
     * @return java.lang.Boolean
     * @author litind
     **/
    @Override
    public Result attention(HttpServletRequest request, User user, University university) {
        Attentionlist attentionlist = new Attentionlist();
        User u = (User) request.getSession().getAttribute("user");
        attentionlist.setId(u.getId());
        if (user==null&&university==null){
            return new Result(400,"传入数据均为空");
        }
        if (user==null){//传入的是学校
            attentionlist.setSchoolid(university.getUniversityId());
            attentionlist.setName(university.getUniversityName());
            attentionlist.setTarget(1);
        }else {//传入的是用户
            attentionlist.setName(user.getUsername());
            attentionlist.setTargetid(user.getId());
            attentionlist.setTarget(0);
        }
        int r = attentionMapper.addAttention(attentionlist);
        if (r>0){
            return new Result(200,"关注成功");
        }else{
            return new Result(400,"关注失败");
        }
    }
    /**
     *
     * 获取用户的关注列表
     * @param id
     * @return
     * @author litind
     **/
    @Override
    public Result getById(Long id) {
        List<Attentionlist> attentionlist = attentionMapper.getByid(id);
        if (attentionlist.isEmpty()){
            return new Result(400,"关注列表为空");
        }else {
            return new Result(200, "获取关注列表成功", attentionlist.size(), attentionlist);
        }
    }
    /**
     *
     * 取消关注
     * @param attentionlist
     * @return
     * @author litind
     **/
    @Override
    public Result delete(HttpServletRequest request, Attentionlist attentionlist) {
        if (attentionlist==null){
            return new Result(400,"传入消息为空,可能未关注");
        }
        User user =(User)request.getSession().getAttribute("user");
        attentionlist.setId(user.getId());
        int r = attentionMapper.delete(attentionlist);
        if (r>0){
            return new Result(200,"取消关注成功");
        }else{
            return new Result(400,"取消关注失败，请联系管理员");
        }
    }
}
