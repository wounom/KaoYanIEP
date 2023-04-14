package com.wounom.kaoyaniep.controller;


import com.wounom.kaoyaniep.entity.Comment;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/14 16:21
 */
@RestController
@RequestMapping("/comment")
@CrossOrigin
@ApiOperation("评论")
public class CommentController {
    @Resource
    private CommentService commentService;
    /**
     * 发表评论
     * @param comment
     * @return
     * @author litind
     **/
    @PostMapping("/post")
    @ApiOperation("发表评论")
    public Result postComment(@RequestBody Comment comment, HttpServletRequest request){
        return commentService.postComment(comment,request);
    }

    /**
     *
     * 获取评论
     * @param tiewenId
     * @return
     * @author litind
     **/
    @GetMapping("/get")
    @ApiOperation("获取评论,传入：content,tiewenId,parentId(选传，子评论传父评论的id)")
    public Result getComment(@RequestParam Long tiewenId){
        return commentService.getComment(tiewenId);
    }
}