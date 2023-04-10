package com.wounom.kaoyaniep.controller;



import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.Tiewen;
import com.wounom.kaoyaniep.service.TieWenService;
import io.swagger.annotations.ApiOperation;
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
}
