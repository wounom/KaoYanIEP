package com.wounom.kaoyaniep.controller;

import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.service.OfficialTieService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

}
