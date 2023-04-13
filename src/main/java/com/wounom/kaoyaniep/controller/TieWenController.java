package com.wounom.kaoyaniep.controller;



import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.Tiewen;
import com.wounom.kaoyaniep.entity.User;
import com.wounom.kaoyaniep.service.TieWenService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/3 9:18
 */
@RestController
@RequestMapping("/tiewen")
public class TieWenController {


    @Resource
    private TieWenService tieWenService;

    /**
     *
     * 收藏贴文
     * @param tiewen,request
     * @return
     * @author litind
     **/
    @PostMapping("/collectTie")
    @ApiOperation("收藏贴文 (title,tiewenId)")
    public Result CollectTie(@RequestBody Tiewen tiewen, HttpServletRequest request){
        return tieWenService.collectTie(tiewen,request);
    }

    /**
     *
     * 通过贴文id查看贴文详情
     * @param tiewenId
     * @return
     * @author litind
     **/
    @GetMapping("/getTiewenByid/{tiewenId}")
    @ApiOperation("查看贴文详情：收藏贴文看详情(tiewenId)")
    public  Result getTiewenByid(@PathVariable(value = "tiewenId") int tiewenId){

        return tieWenService.getTiewenByid(tiewenId);
    }

    /**
     *
     * 通过板块名称获取贴文
     * @param blockName
     * @return
     * @author litind
     **/
    @GetMapping("/getTiewenByBlock")
    @ApiOperation("通过板块名称获取贴文")
    public Result getTiewenByBlock(String blockName){
        return tieWenService.getTiewenByBlock(blockName);
    }

    /**
     *
     * 发布贴文
     * @param tiewen
     * @return
     * @author litind
     **/
    @PostMapping("/postTiewen")
    @ApiOperation("发布贴文" +
            "blockName"+"title"+"content")
    public Result PostTiewen(Tiewen tiewen,HttpServletRequest request){
        return tieWenService.PostTiewen(tiewen,request);
    }

    /**
     *
     * 贴文管理
     * @param request
     * @return
     * @author litind
     **/
    @GetMapping("/getTiewenByuserId")
    @ApiOperation("通过用户id获取贴文")
    public Result ManageTiewen(HttpServletRequest request){
        User user =  (User)request.getSession().getAttribute("user");
        Long userId = user.getId();
        return tieWenService.getTiewenByUserId(userId);
    }

    /**
     *
     * 通过用户id和贴文id删除贴文
     * @param request
     * @return
     * @author litind
     **/
    @DeleteMapping("/deleteByid")
    @ApiOperation("用户删除自己的贴文")
    public Result DeleteTiewen(HttpServletRequest request,@RequestParam(value = "tiewenId") Long tiewenId){
        return tieWenService.deleteTiewen(request,tiewenId);
    }
}
