package com.wounom.kaoyaniep.controller;

import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.service.UniverService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/6 13:36
 */
@RestController
@ApiOperation("院校信息板块")
@CrossOrigin
public class UniverController {
    @Resource
    private UniverService univerService;
    @GetMapping("/getInfo")
    @ApiOperation("通过地区、隶属、特性查询院校")
    public Result GetUniverByCondi(String universityDistrict,String universityHigherup
            ,Integer ifDouble, Integer ifGraduate,Integer ifIndependent){//todo:传输方式可以修改
        return univerService.getUniver(universityDistrict,universityHigherup,ifDouble,ifGraduate,ifIndependent);
    }
}
