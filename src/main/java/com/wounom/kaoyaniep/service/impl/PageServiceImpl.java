package com.wounom.kaoyaniep.service.impl;

import com.wounom.kaoyaniep.dao.PageMapper;
import com.wounom.kaoyaniep.entity.FirstpagePush;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.TiewenOfficial;
import com.wounom.kaoyaniep.service.PageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/4 9:47
 */
@Service
public class PageServiceImpl implements PageService {
    @Resource
    private PageMapper pageMapper;
    @Override
    public Result getFPP() {
        List<FirstpagePush> data =  pageMapper.selectFpp();
        if (data.isEmpty()){
            return new Result(400,"推送表为空", data.size(),data);
        }
        System.out.println(data.get(0).getFirstId());
        return new Result(200,"获取成功",data.size(),data);
    }

    /**
     * 获取官方特别版贴
     *
     * @param blockName
     * @return com.wounom.kaoyaniep.entity.Result
     * @author litind
     **/
    @Override
    public Result getOfficialTieByblockName(String blockName) {
        List<TiewenOfficial> list = pageMapper.getOfficialTieByBlockName(blockName);
        if (list.size()>0){
            return new Result(200,"获取成功",list.size(),list);
        }else {
            return new Result(400,"获取失败,该板块内容为空",0,null);
        }
    }
}
