package com.wounom.kaoyaniep.controller;

import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.service.PageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/4 9:41
 */
@RestController
@RequestMapping("/firstpage")
public class FirstPageController {

    @Resource
    private PageService pageService;

    /**
     *
     * 通过id获取首页推送
     * @return com.wounom.kaoyaniep.entity.Result
     * @author litind
     **/
    @GetMapping("/getFpp")
    @ApiOperation("获取首页推送")
    public Result getFpp(){
        return  pageService.getFPP();
    }
}
