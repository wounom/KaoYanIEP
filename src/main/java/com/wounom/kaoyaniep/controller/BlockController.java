package com.wounom.kaoyaniep.controller;

import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.service.BlockService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/7 8:21
 */
@RestController
public class BlockController {
    @Resource
    private BlockService blockService;
    /**
     *
     * 板块管理：获取板块
     * @param
     * @return
     * @author litind
     **/
    @GetMapping("/getBlock")
    @ApiOperation("管理员：板块管理，获取板块列表")
    public Result getBlock(){
       return blockService.getBlock();
    }

    /**
     *
     * 删除板块 (管理员删除板块)
     * @param status,blockName
     * @return
     * @author litind
     **/
    @PostMapping("/deleteBlock")
    @ApiOperation("删除板块，记得去TiewenController删除板块内数据")
    public Result deleteBlock(int status,String blockName){
        if(status == 1 || status == 2){
            return  new Result(400,"官方必要板块，不允许删除");
        } else{ //删除院校板块
           return  blockService.deleteBlock(blockName);
        }
    }
}
