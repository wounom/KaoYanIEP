package com.wounom.kaoyaniep.controller;



import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.Tiewen;
import com.wounom.kaoyaniep.service.TieWenService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    @ApiOperation("收藏贴文")
    public Result CollectTie(Tiewen tiewen, HttpServletRequest request){
        return tieWenService.collectTie(tiewen,request);
    }

    /**
     *
     * 通过贴文id查看贴文详情
     * @param tiewenId
     * @return
     * @author litind
     **/
    @PostMapping("/getTiewenByid")
    @ApiOperation("查看贴文详情：收藏贴文看详情")
    public  Result getTiewenByid(int tiewenId){
        return tieWenService.getTiewenByid(tiewenId);
    }
}
