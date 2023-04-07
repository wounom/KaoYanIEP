package com.wounom.kaoyaniep.controller;



import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.service.TieWenService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
     * 获取需要审核的贴文
     * @param
     * @return
     * @author litind
     **/
    @GetMapping("/getCheckTiewen")
    @ApiOperation("获取需要审核的贴文")
    public Result getCheckTiewen(){
        return tieWenService.getCheckTiewen();
    }
    /**
     *
     * 审核管理
     * @param status
     * @return
     * @author litind
     **/
    @PostMapping("/checktiewen")
    @ApiOperation("审核推文")
    public Result CheckTiewen(int tiewenId,int status){
        //删除为-1
        //过审为1
        return tieWenService.checkTiewen(tiewenId,status);
    }
    /**
     *
     * 获取用户发帖
     * @param userId
     * @return
     * @author litind
     **/
    @PostMapping("/getTiewenByUserid")
    @ApiOperation("通过用户id获取帖子")
    public Result GetTieWenByUser(int userId/*HttpServletRequest request*/){
        /*User user = (User) request.getSession().getAttribute("user");
        int userId = (int) user.getId();*/
        return tieWenService.getTiewenByUser(userId);
    }

    /**
     *
     * 管理员删除板块的同时删除板块内数据
     * @param blockName
     * @return
     * @author litind
     **/
    @PostMapping("/deleteByBlock")
    @ApiOperation("删除板块时，同时删除板块数据")
    public Result deleteTiewenByBlock(String blockName){
        return tieWenService.deleteTiewenByBlock(blockName);
    }

}
