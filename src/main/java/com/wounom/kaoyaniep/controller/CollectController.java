package com.wounom.kaoyaniep.controller;

import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.service.CollectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/13 10:54
 */
@RestController
@RequestMapping("collet")
public class CollectController {
    @Resource
    private CollectService collectService;
    /**
     *
     * 获取用户文章de收藏列表
     * @param request
     * @return
     * @author litind
     **/
    @GetMapping("/getCollectlistArticle")
    @ApiOperation("获取用户文章收藏列表")
    public Result getCollectlistArticle(HttpServletRequest request){
        return collectService.getCollectlistArticle(request);
    }

    /**
     *
     * 获取用户板块de收藏列表
     * @param request
     * @return
     * @author litind
     **/
    @GetMapping("/getCollectlistBlock")
    @ApiOperation("获取用户板块收藏列表")
    public Result getCollectlistBlock(HttpServletRequest request){
        return collectService.getCollectlistBlock(request);
    }

    /**
     *
     * 获取用户贴文de收藏列表
     * @param request
     * @return
     * @author litind
     **/
    @GetMapping("/getCollectlistTiewen")
    @ApiOperation("获取用户贴文收藏列表")
    public Result getCollectlistTiewen(HttpServletRequest request){
        return collectService.getCollectlistTiewen(request);
    }
    /**
     *
     * 删除用户收藏de文章
     * @param
     * @return
     * @author litind
     **/
    @DeleteMapping("/deleteCollectArticle")
    @ApiOperation("删除收藏文章(aid)")
    public Result deleteArticleById(@RequestParam("aid") Long aid, HttpServletRequest request){
        /*JSONObject json = JSON.parseObject(String.valueOf(aid));
        aid = json.getLong("aid");*/
        return collectService.deleteArticleById(aid,request);
    }
    /**
     *
     * 删除用户收藏de贴文
     * @param
     * @return
     * @author litind
     **/
    @DeleteMapping("/deleteCollectTiewen")
    @ApiOperation("删除收藏贴文(tid)")
    public Result deleteTiewenById(@RequestParam("tid") Long tid, HttpServletRequest request){
        /*JSONObject json = JSON.parseObject(tid);
        Long  id = json.getLong("value");*/
        /*Long tid = map.get("tid");*/
        return collectService.deleteTiewenById(tid,request);
    }

    /**
     *
     * 删除用户收藏de板块
     * @param
     * @return
     * @author litind
     **/
    @DeleteMapping("/deleteCollectBlock")
    @ApiOperation("删除收藏板块(bName)")
    public Result deleteBlockById(@RequestParam("bName") String bName, HttpServletRequest request){
       /* JSONObject json = JSON.parseObject(bName);
        bName = json.getString("bName");*/
        return collectService.deleteBlockById(bName,request);
    }

}
