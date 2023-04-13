package com.wounom.kaoyaniep.service.impl;

import com.wounom.kaoyaniep.dao.CollectMapper;
import com.wounom.kaoyaniep.entity.*;
import com.wounom.kaoyaniep.service.CollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/13 10:56
 */
@Service
public class CollectServiceImpl implements CollectService {
    @Resource
    private CollectMapper collectMapper;
    /**
     *
     * 获取文章收藏列表
     * @param request
     * @return java.lang.Boolean
     * @author litind
     **/
    @Override
    public Result getCollectlistArticle(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String userEmail = user.getEmail();
        List<Collectlistarticle> list =  collectMapper.getCollectlistA(userEmail);
        if (list.size()>0){
            return new Result(200,"获取成功", list.size(),list);
        }else {
            return new Result(400,"数据为空");
        }
    }

    /**
     *
     * 获取贴文收藏列表
     * @param request
     * @return java.lang.Boolean
     * @author litind
     **/
    @Override
    public Result getCollectlistBlock(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String userEmail = user.getEmail();
        List<Collectlistblock> list =  collectMapper.getCollectlistB(userEmail);
        if (list.size()>0){
            return new Result(200,"获取成功", list.size(),list);
        }else {
            return new Result(400,"数据为空");
        }
    }
    /**
     *
     * 获取贴文收藏列表
     * @param request
     * @return java.lang.Boolean
     * @author litind
     **/
    @Override
    public Result getCollectlistTiewen(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String userEmail = user.getEmail();
        List<Collectlisttiewen> list =  collectMapper.getCollectlistT(userEmail);
        if (list.size()>0){
            return new Result(200,"获取成功", list.size(),list);
        }else {
            return new Result(400,"数据为空");
        }
    }

    /**
     *
     * 删除文章收藏
     * @param aid,request
     * @return java.lang.Boolean
     * @author litind
     **/
    @Override
    public Result deleteArticleById(Long aid, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Collectlistarticle collectlistarticle = new Collectlistarticle();
        collectlistarticle.setAid(aid);
        collectlistarticle.setUserEmail(user.getEmail());
        int r = collectMapper.deleteCollectArticleByid(collectlistarticle);
        if (r>0){
            return new Result(200,"删除成功");
        }else{
            return new Result(400,"删除失败");
        }
    }

    /**
     *
     * 删除贴文收藏
     * @param tid,request
     * @return java.lang.Boolean
     * @author litind
     **/
    @Override
    public Result deleteTiewenById(Long tid, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Collectlisttiewen collectlisttiewen = new Collectlisttiewen();
        collectlisttiewen.setTid(tid);
        collectlisttiewen.setUserEmail(user.getEmail());
        int r = collectMapper.deleteCollectTiewenByid(collectlisttiewen);
        if (r>0){
            return new Result(200,"删除成功");
        }else{
            return new Result(400,"删除失败");
        }
    }


    /**
     *
     * 删除收藏板块
     * @param bName,request
     * @return java.lang.Boolean
     * @author litind
     **/
    @Override
    public Result deleteBlockById(String bName, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Collectlistblock collectlistblock = new Collectlistblock();
        collectlistblock.setBName(bName);
        collectlistblock.setUserEmail(user.getEmail());
        int r = collectMapper.deleteCollectBlockByid(collectlistblock);
        if (r>0){
            return new Result(200,"删除成功");
        }else{
            return new Result(400,"删除失败");
        }
    }
}
