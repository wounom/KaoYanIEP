package com.wounom.kaoyaniep.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.wounom.kaoyaniep.entity.Collectlist;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.Tiewen;
import com.wounom.kaoyaniep.service.CollectService;
import io.swagger.annotations.ApiOperation;
import jdk.vm.ci.meta.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
     * 获取单个收藏
     * @param
     * @return
     * @author litind
     **/
    @GetMapping("/getSingle")
    @ApiOperation("通过页面target,tid,cName获取是否收藏")
    public Result getSingle(HttpServletRequest request,@RequestParam("targetName") String targetName,@RequestParam(value = "target") Integer target,@RequestParam(value = "tid") Long tid){
        Collectlist collectlist = new Collectlist();
        collectlist.setTid(tid);
        collectlist.setTarget(target);
        collectlist.setTargetName(targetName);
        return collectService.getCollectSingle(request,collectlist);
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
    @ApiOperation("收藏接口三合一 (targetName(关注对象的名字),tid(关注对象的id),target（-1为官方贴文(文章)，0为版块，1为贴文）)")
    public Result CollectTie(@RequestBody Collectlist collectlist, HttpServletRequest request){
        return collectService.collect(collectlist,request);
    }


    /**
     *
     * 删除操作
     * @param id,request
     * @return com.wounom.kaoyaniep.entity.Result
     * @author litind
     **/
    @DeleteMapping("/delete")
    @ApiOperation("删除收藏(id)")
    public Result delete(@RequestParam(value = "id") Long id, HttpServletRequest request){
        return collectService.delete(id,request);
    }

    /**
     *
     * 批量删除
     * @param idmap
     * @return
     * @author litind
     **/
    @DeleteMapping("/deleteList")
    @ApiOperation("批量删除收藏")
    public Result deleteList(@RequestBody Map<String,List<Long>> idmap, HttpServletRequest request){
        List<Long> id = idmap.get("id");
        System.out.println(id);
        return collectService.deleteList(id,request);
    }

}
