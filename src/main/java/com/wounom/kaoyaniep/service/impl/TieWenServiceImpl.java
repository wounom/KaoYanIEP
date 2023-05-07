package com.wounom.kaoyaniep.service.impl;



import cn.hutool.core.date.DateTime;
import com.wounom.kaoyaniep.dao.BlockMapper;
import com.wounom.kaoyaniep.dao.CommentMapper;
import com.wounom.kaoyaniep.dao.TieWenMapper;
import com.wounom.kaoyaniep.dao.UserMapper;
import com.wounom.kaoyaniep.entity.*;
import com.wounom.kaoyaniep.service.TieWenService;
import com.wounom.kaoyaniep.utils.TokenUtils;
import org.apache.catalina.webresources.TomcatJarInputStream;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Resource
    private CommentMapper commentMapper;

    @Resource
    private BlockMapper blockMapper;
    @Resource
    private UserMapper userMapper;
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
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
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
    public Result getTiewenByid(Long tiewenId) {
        Tiewen tiewen  =  tieWenMapper.getTiewenById(tiewenId);
        if (tiewen.getUserId()!=0){
            User user = userMapper.getUserById(tiewen.getUserId());
            tiewen.setImage(user.getImage());
        }else {
            tiewen.setImage(null);
        }
        if (tiewen!=null){
            return new Result(200,"获取成功",1,tiewen);
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
        for (int i = 0;i<tiewenList.size();i++){
            Long tiewenId = tiewenList.get(i).getTiewenId();
            //查询贴文最新评论
            Comment comment = commentMapper.getLastBytiewenId(tiewenId);
            tiewenList.get(i).setComment(comment);
        }
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
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
        tiewen.setUserId(user.getId());
        tiewen.setUsername(user.getUsername());
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
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
        tiewen.setUserId(user.getId());
        Tiewen newTiewen = tieWenMapper.getTiewenById(tiewenId);
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
