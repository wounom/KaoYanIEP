package com.wounom.kaoyaniep.controller;

import com.wounom.kaoyaniep.entity.Attentionlist;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.University;
import com.wounom.kaoyaniep.entity.User;
import com.wounom.kaoyaniep.service.AttentionService;
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
     * 关注用户
     * @param user，university
     * @return
     * @author litind
     **/
    @PostMapping("/add")
    @ApiOperation("关注用户传入user，关注学校传入university")
    public Result attention(HttpServletRequest request, @RequestBody(required = false) User user, @RequestBody(required = false) University university){
        return attentionService.attention(request,user,university);
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
        User user = (User) request.getSession().getAttribute("user");
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
    @Delete("/delete")
    @ApiOperation("取消关注")
    public Result deleteAttention(HttpServletRequest request, @RequestBody Attentionlist attentionlist){
        return attentionService.delete(request,attentionlist);
    }

}
