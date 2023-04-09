package com.wounom.kaoyaniep.service.impl;


import cn.hutool.core.date.DateTime;
import com.wounom.kaoyaniep.dao.OfficialTieMapper;
import com.wounom.kaoyaniep.entity.*;

import com.wounom.kaoyaniep.service.OfficialTieService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/6 12:49
 */

@Service
public class OfficialTieServiceImpl implements OfficialTieService {
    @Resource
    private OfficialTieMapper officalTieMapper;

    /**
     * 获取官方特别版贴
     *
     * @param blockName
     * @return com.wounom.kaoyaniep.entity.Result
     * @author litind
     **/
    @Override
    public Result getOfficialTieByblockName(String blockName) {
        List<TiewenOfficial> list = officalTieMapper.getOfficialTieByBlockName(blockName);
        if (list.size()>0){
            return new Result(200,"获取成功",list.size(),list);
        }else {
            return new Result(400,"获取失败,该板块内容为空",0,null);
        }
    }

    /**
     *
     * 获取热帖
     * @return com.wounom.kaoyaniep.entity.Result
     * @author litind
     **/
    @Override
    public Result getOfficialTieByTime() {
        List<TiewenOfficial> list = officalTieMapper.getOfficialTieByTime();
        if (list.size()!=0){
            return new Result(200,"获取成功",list.size(),list);
        }else {
            return new Result(400,"获取失败,可能无数据");
        }
    }

    /**
     * 收藏文章
     * @param aName,request
     * @return
     * @author litind
     **/
    @Override
    public Result collectArticle(String aName, HttpServletRequest request) {
        Collectlistarticle collectlistarticle = new Collectlistarticle();
        User user = (User) request.getSession().getAttribute("user");
        collectlistarticle.setAName(aName);
        collectlistarticle.setUserEmail(user.getEmail());
        collectlistarticle.setCollecttime(DateTime.now());
        int r = officalTieMapper.insertCollectlist(collectlistarticle);
        if (r>0){
            return new Result(200,"收藏成功");
        }else {
            return new Result(400,"收藏失败");
        }
    }
}
