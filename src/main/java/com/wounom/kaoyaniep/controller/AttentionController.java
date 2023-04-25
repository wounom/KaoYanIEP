package com.wounom.kaoyaniep.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.wounom.kaoyaniep.entity.Attentionlist;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.University;
import com.wounom.kaoyaniep.entity.User;
import com.wounom.kaoyaniep.service.AttentionService;
import com.wounom.kaoyaniep.utils.TokenUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/13 10:34
 */
@RestController
@RequestMapping("/attention")
@CrossOrigin
public class AttentionController {
    @Resource
    private AttentionService attentionService;
    /**
     *
     * 判断用户是否已经关注该用户（院校）
     * @param
     * @return
     * @author litind
     **/
    @GetMapping("/getIf")
    @ApiOperation("判断是否关注过")
    public Result getIf(HttpServletRequest request,@RequestParam(value = "targetid",required = false) Long targetid,@RequestParam(value = "schoolid",required = false) Long schoolid){
        return attentionService.getIf(request,targetid,schoolid);
    }

    /**
     *
     * 关注用户
     * @param attentionlist
     * @return
     * @author litind
     **/
    @PostMapping("/add")
    @ApiOperation("关注用户传入user，关注学校传入university")
    public Result attention(HttpServletRequest request, @RequestBody Attentionlist attentionlist){
        return attentionService.attention(request,attentionlist);
    }

    /**
     *
     * 获取某个用户的关注列表
     * @param request
     * @return
     * @author litind
     **/
    @GetMapping("/getAll")
    @ApiOperation("获取用户的关注列表")
    public Result getAttention(HttpServletRequest request){
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
        Long id = user.getId();
        return attentionService.getById(id);
    }

    /**
     *
     * 取消关注
     * @param
     * @return
     * @author litind
     **/
    @DeleteMapping("/delete")
    @ApiOperation("取消关注")
    public Result deleteAttention(HttpServletRequest request, @RequestBody Attentionlist attentionlist){
        return attentionService.delete(request,attentionlist);
    }

}
