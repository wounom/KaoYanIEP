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
@RequestMapping("/block")
@CrossOrigin
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
    @GetMapping("/getByDistrict")
    @ApiOperation("研友论坛中获取板块列表与板块信息")
    public Result getBlockByDistrict(@RequestParam("district") String district){
       return blockService.getBlockByDistrict(district);
    }


    /**
     *
     * 模糊搜索板块内的贴文
     * @param blockName,keywords
     * @return
     * @author litind
     **/
    @GetMapping("/search")
    @ApiOperation("模糊搜索板块内的贴文")
    public Result Search(@RequestParam String blockName,@RequestParam String keywords){

        return blockService.search(blockName,keywords);
    }

}
