package com.wounom.kaoyaniep.controller;

import com.wounom.kaoyaniep.entity.Collectlist;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.Tiewen;
import com.wounom.kaoyaniep.service.CollectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/13 10:54
 */
@RestController
@RequestMapping("collect")
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
    @GetMapping("/getlistArticle")
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
    @GetMapping("/getlistBlock")
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
    @GetMapping("/getlistTiewen")
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
    @DeleteMapping("/deleteArticle")
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
    @DeleteMapping("/deleteTiewen")
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
    @DeleteMapping("/deleteBlock")
    @ApiOperation("删除收藏板块(bName)")
    public Result deleteBlockById(@RequestParam("bName") String bName, HttpServletRequest request){
       /* JSONObject json = JSON.parseObject(bName);
        bName = json.getString("bName");*/
        return collectService.deleteBlockById(bName,request);
    }





    /**
     *
     * 获取收藏列表
     * @param request,target
     * @return
     * @author litind
     **/
    @GetMapping("/get")
    @ApiOperation("获取用户收藏列表,target（-1为文章，0为版块，1为贴文）")
    public Result getlist(HttpServletRequest request,@RequestParam int target){
        return collectService.getCollectlist(request,target);
    }

    /**
     *
     * 收藏操作
     * @param collectlist,request
     * @return
     * @author litind
     **/
    @PostMapping("/post")
    @ApiOperation("收藏贴文 (cName,tid,target（-1为文章，0为版块，1为贴文）)")
    public Result CollectTie(@RequestBody Collectlist collectlist, HttpServletRequest request){
        return collectService.collect(collectlist,request);
    }
    /**
     *
     * 删除操作
     * @param collectlist,request
     * @return com.wounom.kaoyaniep.entity.Result
     * @author litind
     **/
    @DeleteMapping("/delete")
    @ApiOperation("删除收藏板块(tid,target)")
    public Result delete(@RequestBody Collectlist collectlist, HttpServletRequest request){
        return collectService.delete(collectlist,request);
    }
    /**
     *
     * 批量删除
     * @param id
     * @return
     * @author litind
     **/
    @DeleteMapping("/deleteList")
    @ApiOperation("批量删除收藏")
    public Result deleteList(@RequestParam List<Long> id, HttpServletRequest request){
        System.out.println(id);
        return collectService.deleteList(id,request);
    }

}
