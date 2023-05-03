package com.wounom.kaoyaniep.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.api.R;
import com.wounom.kaoyaniep.dao.BlockMapper;
import com.wounom.kaoyaniep.entity.*;
import com.wounom.kaoyaniep.service.BlockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/7 8:22
 */
@Service
public class BlockServiceImpl implements BlockService {
    @Resource
    private BlockMapper blockMapper;
    @Override
    public Result getBlockByStatus(int status) {
        List<Block> blockList =  blockMapper.getBlockByStatus(status);
        if(blockList.size()>0){
            return new Result(200,"获取成功",blockList.size(),blockList);
        }else {
            return new Result(400,"获取失败，该项目下无板块");
        }
    }

    /**
     *
     * 模糊搜索
     * @param blockName,keywords
     * @return
     * @author litind
     **/
    @Override
    public Result search(String blockName, String keywords) {
        List<Tiewen> list =  blockMapper.search(blockName,keywords);
        if (list.size()>0){
            return new Result(200,"message",list.size(),list);
        }else {
            return new Result(400,"无内容",0,null);
        }

    }
}
