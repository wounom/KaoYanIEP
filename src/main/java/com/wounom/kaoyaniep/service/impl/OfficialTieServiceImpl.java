package com.wounom.kaoyaniep.service.impl;


import com.wounom.kaoyaniep.dao.OfficialTieMapper;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.TiewenOfficial;

import com.wounom.kaoyaniep.service.OfficialTieService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Override
    public Result getOfficialTieByblockName(String blockName) {
        List<TiewenOfficial> list = officalTieMapper.getOfficialTieByBlockName(blockName);
        if (list.size()>0){
            return new Result(200,"获取成功",list.size(),list);
        }else {
            return new Result(400,"获取失败,该板块内容为空",0,null);
        }
    }
}
