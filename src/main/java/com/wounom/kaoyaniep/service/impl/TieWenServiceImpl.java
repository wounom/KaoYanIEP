package com.wounom.kaoyaniep.service.impl;



import cn.hutool.core.date.DateTime;
import com.wounom.kaoyaniep.dao.TieWenMapper;
import com.wounom.kaoyaniep.entity.*;
import com.wounom.kaoyaniep.service.TieWenService;
import org.apache.catalina.webresources.TomcatJarInputStream;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.management.LockInfo;
import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/3 9:18
 */
@Service
public class TieWenServiceImpl implements TieWenService {
    @Resource
    private TieWenMapper tieWenMapper;

    /**
     *
     * 收藏帖子
     * @param tiewen,request
     * @return
     * @author litind
     **/
    @Override
    public Result collectTie(Tiewen tiewen, HttpServletRequest request) {
        Collectlisttiewen collectlisttiewen = new Collectlisttiewen();
        User user = (User) request.getSession().getAttribute("user");
        collectlisttiewen.setTName(tiewen.getTitle());
        collectlisttiewen.setTid(tiewen.getTiewenId());
        collectlisttiewen.setUserEmail(user.getEmail());
        collectlisttiewen.setCollecttime(DateTime.now());
        int r = tieWenMapper.insertCollectlist(collectlisttiewen);
        if (r>0){
            return new Result(200,"收藏成功");
        }else {
            return new Result(400,"收藏失败");
        }
    }
    /**
     *
     * 通过贴文id获取贴文详情
     * @param tiewenId
     * @return
     * @author litind
     **/
    @Override
    public Result getTiewenByid(int tiewenId) {
        List<Tiewen> tiewenList  =  tieWenMapper.getTiewenById(tiewenId);
        if (tiewenList.size()>0){
            return new Result(200,"获取成功",tiewenList.size(),tiewenList);
        }else {
            return new Result(400,"获取失败，数据为空");
        }
    }
    /**
     *
     * 通过板块名称获取贴文
     * @param blockName
     * @return
     * @author litind
     **/
    @Override
    public Result getTiewenByBlock(String blockName) {
        List<Tiewen> tiewenList = tieWenMapper.getTiewenByBlock(blockName);
        if (tiewenList.size()>0){
            return new Result(200,"获取成功",tiewenList.size(),tiewenList);
        }else {
            return new Result(400,"获取失败，数据为空");
        }
    }
    /**
     *
     * 上传贴文
     * @param tiewen,request
     * @return
     * @author litind
     **/
    @Override
    public Result PostTiewen(Tiewen tiewen, HttpServletRequest request) {
        //获取发该贴文的用户信息
        User user =(User) request.getSession().getAttribute("user");
        tiewen.setUserId(user.getId());
        tiewen.setCreateTime(DateTime.now());
        int r = tieWenMapper.PostTiewen(tiewen);
        if (r>0){
            return new Result(200,"发布成功");
        }else {
            return new Result(400,"发布失败");
        }
    }

    /**
     *
     * 通过用户id获取帖子
     * @param userId
     * @return
     * @author litind
     **/
    @Override
    public Result getTiewenByUserId(Long userId) {
        List<Tiewen> tiewenList =  tieWenMapper.getTieWenbyUserId(userId);
        if (tiewenList.size()>0){
            return new Result(200,"获取成功",tiewenList.size(),tiewenList);
        }else {
            return new Result(400,"获取失败，数据为空");
        }
    }

    /**
     *
     * 删除用户贴文
     * @param request
     * @param tiewenId
     * @return com.wounom.kaoyaniep.entity.Result
     * @author litind
     **/
    @Override
    public Result deleteTiewen(HttpServletRequest request, Long tiewenId) {
        Tiewen tiewen = new Tiewen();
        tiewen.setTiewenId(tiewenId);
        User user = (User)request.getSession().getAttribute("user");
        tiewen.setUserId(user.getId());
        int r = tieWenMapper.deleteTiewenByid(tiewen);
        if (r>0){
            return new Result(200,"删除成功");
        }else {
            return new Result(400,"删除失败，帖子可能已经不存在或不是您的帖子");
        }
    }

    /**
     *
     * 获取热门贴文
     * @param
     * @return
     * @author litind
     **/
    @Override
    public Result getHot() {
        List<Tiewen> list =  tieWenMapper.getHot();
        return new Result(200,"success",list.size(),list);
    }
}
