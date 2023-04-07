package com.wounom.kaoyaniep.service.impl;

import com.wounom.kaoyaniep.dao.BlockMapper;
import com.wounom.kaoyaniep.entity.Block;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.service.BlockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public Result getBlock() {
        List<Block> blockList =  blockMapper.getBlock();
        if(blockList.size()>0){
            return new Result(200,"获取成功",blockList.size(),blockList);
        }else {
            return new Result(400,"获取失败，该项目下无板块");
        }
    }

    @Override
    public Result deleteBlock(String blockName) {
        int r = blockMapper.deleteBlock(blockName);
        if (r>0){
            return new Result(200,"删除成功");
        }else{
            return new Result(400,"系统错误");
        }
    }
}
