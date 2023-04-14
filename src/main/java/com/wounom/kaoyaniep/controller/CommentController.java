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

    /**
     *
     * 删除评论
     * @param
     * @return
     * @author litind
     **/
    @DeleteMapping("/delete")
    @ApiOperation("通过用户id评论id删除评论")
    public Result deleteComment(@RequestParam Long id,HttpServletRequest request){
        return commentService.deleteComment(id,request);
    }

    /**
     *
     * 获取用户贴文的评论
     * @param request
     * @return
     * @author litind
     **/
    @GetMapping("/getByuser")
    @ApiOperation("用户获取自己发布的帖子下的评论")
    public Result getCommentByUser(HttpServletRequest request){
        return commentService.getCommentByUser(request);
    }
}