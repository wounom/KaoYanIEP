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
    public Result deleteList(@RequestBody List<Long> id, HttpServletRequest request){
        System.out.println(id);
        return collectService.deleteList(id,request);
    }

}
