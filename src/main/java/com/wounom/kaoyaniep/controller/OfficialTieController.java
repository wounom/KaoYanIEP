package com.wounom.kaoyaniep.controller;

import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.TiewenOfficial;
import com.wounom.kaoyaniep.service.OfficialTieService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/6 12:42
 */
@RestController
@RequestMapping("/specialTie")
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
    @GetMapping("/getspecialTie")
    @ApiOperation("获取特别专版的官方帖子")
    public Result getSpecialTie(String blockName){
        return officalTieService.getOfficialTieByblockName(blockName);

    }
    /**
     *
     * 获取热门官方贴
     * @param
     * @return
     * @author litind
     **/
    @GetMapping("/gethot")
    @ApiOperation("/获取热门官方贴")
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
    @PostMapping("/collectArticle")
    @ApiOperation("收藏文章")
    public Result Collect(TiewenOfficial tiewenOfficial, HttpServletRequest request){
        return officalTieService.collectArticle(tiewenOfficial,request);
    }

    /**
     *
     * 通过官方文章id获取文章
     * @param
     * @return
     * @author litind
     **/
    @PostMapping("/getofficialTieById")
    @ApiOperation("通过文章id获取文章")
    public Result getofficialTieById(int tiewenId){
       return officalTieService.getofficialTieById(tiewenId);
    }
}
