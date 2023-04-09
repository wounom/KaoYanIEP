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
     * @param tName,request
     * @return
     * @author litind
     **/
    @Override
    public Result collectTie(String tName, HttpServletRequest request) {
        Collectlisttiewen collectlisttiewen = new Collectlisttiewen();
        User user = (User) request.getSession().getAttribute("user");
        collectlisttiewen.setTName(tName);
        collectlisttiewen.setUserEmail(user.getEmail());
        collectlisttiewen.setCollecttime(DateTime.now());
        int r = tieWenMapper.insertCollectlist(collectlisttiewen);
        if (r>0){
            return new Result(200,"收藏成功");
        }else {
            return new Result(400,"收藏失败");
        }
    }
}
