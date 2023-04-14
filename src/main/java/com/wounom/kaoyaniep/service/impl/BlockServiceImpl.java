package com.wounom.kaoyaniep.service.impl;

import cn.hutool.core.date.DateTime;
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
    public Result getBlockByDistrict(String district) {
        List<Block> blockList =  blockMapper.getBlockByDistrict(district);
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
    /**
     *
     * 收藏
     * @param bName,request
     * @return
     * @author litind
     **/
    @Override
    public Result collectBlock(String bName, HttpServletRequest request) {
        Collectlistblock collectlistblock = new Collectlistblock();
        User user = (User) request.getSession().getAttribute("user");
        collectlistblock.setBName(bName);
        collectlistblock.setUserEmail(user.getEmail());
        collectlistblock.setCollecttime(DateTime.now());
        int r = blockMapper.insertCollectlist(collectlistblock);
        if (r>0){
            return new Result(200,"收藏成功");
        }else {
            return new Result(400,"收藏失败");
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
        return null;
    }
}
