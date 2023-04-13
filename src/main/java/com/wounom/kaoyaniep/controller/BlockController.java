package com.wounom.kaoyaniep.controller;

import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.service.BlockService;
import io.swagger.annotations.ApiOperation;
import jdk.vm.ci.meta.Value;
import org.apache.ibatis.annotations.Insert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
     * 获取板块
     * @param
     * @return
     * @author litind
     **/
    @GetMapping("/getBlockByDistrict")
    @ApiOperation("研友论坛中获取板块列表与板块信息")
    public Result getBlockByDistrict(@RequestParam("district") String district){
       return blockService.getBlockByDistrict(district);
    }

    /**
     *
     * 收藏板块
     * @param
     * @return
     * @author litind
     **/
    @PostMapping("/collectblock/{bName}")
    @ApiOperation("收藏板块")
    public Result CollectBlock( @RequestParam(value = "bName") String bName, HttpServletRequest request){
        return blockService.collectBlock(bName,request);
    }

}
