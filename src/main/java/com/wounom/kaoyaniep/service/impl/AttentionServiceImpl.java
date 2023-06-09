package com.wounom.kaoyaniep.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.wounom.kaoyaniep.dao.AttentionMapper;
import com.wounom.kaoyaniep.entity.*;
import com.wounom.kaoyaniep.service.AttentionService;
import com.wounom.kaoyaniep.utils.TokenUtils;
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
     * @param
     * @return 
     * @author litind
     **/
    @Override
    public Result getIf(HttpServletRequest request, Long targetid, Long schoolid,int target) {
        String token = request.getHeader("token");
        User u = TokenUtils.getUser(token);
        Attentionlist attentionlist = new Attentionlist();
        attentionlist.setUserId(u.getId());
        attentionlist.setTargetid(targetid);
        attentionlist.setSchoolid(schoolid);
        attentionlist.setTarget(target);
        Attentionlist alist = null;
        if (target==1){//查询学校
            alist = attentionMapper.findIfSchool(attentionlist);
        }else {
            alist = attentionMapper.findIfUser(attentionlist);
        }
        boolean b = false;
        if (alist!=null){
            b = true;
        }
        if (b){
            return new Result(200,"已关注",1,b);
        }else {
            return new Result(400,"未关注",0,b);
        }
    }

    /**
     *
     * 关注用户或者院校
     * @param attentionlist
     * @return java.lang.Boolean
     * @author litind
     **/
    @Override
    public Result attention(HttpServletRequest request, Attentionlist attentionlist) {
        String token = request.getHeader("token");
        User u = TokenUtils.getUser(token);
        if (attentionlist==null){
            return new Result(400,"传入数据均为空");
        }
        attentionlist.setUserId(u.getId());
        Attentionlist attentionlist1 = null;
        if (attentionlist.getTarget()==1){
             attentionlist1 = attentionMapper.findIfSchool(attentionlist);
        }else {
             attentionlist1 = attentionMapper.findIfUser(attentionlist);
        }
        if (attentionlist1!=null){
            return new Result(400,"已关注，请勿重复关注");
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
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
        attentionlist.setUserId(user.getId());
        int r =0;
        if (attentionlist.getTarget()==1){
             r = attentionMapper.deleteAttentionUniver(attentionlist);
        }else {
            r = attentionMapper.deleteAttentionUser(attentionlist);
        }
        if (r>0){
            return new Result(200,"取消关注成功");
        }else{
            return new Result(400,"取消关注失败，请联系管理员");
        }
    }
}
