package com.wounom.kaoyaniep.service.impl;



import cn.hutool.core.date.DateTime;
import com.wounom.kaoyaniep.dao.TieWenMapper;
import com.wounom.kaoyaniep.entity.*;
import com.wounom.kaoyaniep.service.TieWenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
}
