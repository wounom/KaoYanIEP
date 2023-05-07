package com.wounom.kaoyaniep.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.TiewenOfficial;
import com.wounom.kaoyaniep.service.OfficialTieService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/6 12:42
 */
@RestController
@RequestMapping("/specialTie")
@CrossOrigin
public class OfficialTieController {
    @Resource
    private OfficialTieService officalTieService;
    /**
     *
     * 获取特别专版的官方帖子
     * @param blockName
     * @return
     * @author litind
     **/
    @GetMapping("/getspecialTie/{blockName}")
    @ApiOperation("获取官方帖子(blockName)")
    public Result getSpecialTie(@PathVariable String blockName){
        return officalTieService.getOfficialTieByblockName(blockName);
    }
    /**
     *
     * 获取热门官方贴
     * @param
     * @return
     * @author litind
     **/
    @GetMapping("/getLast")
    @ApiOperation("/获取最新官方贴")
    public Result getHotOfficalTie(){
        return officalTieService.getOfficialTieByTime();
    }
    
    /**
     *
     * 收藏官方贴
     * @param
     * @return 
     * @author litind
     **/
    /*@PostMapping("/collectArticle")
    @ApiOperation("收藏文章 (title,tiewenId)")
    public Result Collect(@RequestBody TiewenOfficial tiewenOfficial, HttpServletRequest request){
        return officalTieService.collectArticle(tiewenOfficial,request);
    }*/


    /**
     *
     * 通过官方文章id获取文章
     * @param
     * @return
     * @author litind
     **/
    @GetMapping("/getofficialTieById/{tiewenId}")
    @ApiOperation("通过文章id获取文章")
    public Result getofficialTieById(@PathVariable int tiewenId){
        System.out.println(tiewenId);
       return officalTieService.getofficialTieById(tiewenId);
    }
}

