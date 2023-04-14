package com.wounom.kaoyaniep.service;

import com.wounom.kaoyaniep.entity.Comment;
import com.wounom.kaoyaniep.entity.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/14 16:35
 */
public interface CommentService {
    Result postComment(Comment comment, HttpServletRequest request);

    Result getComment(Long tiewenId);

    Result deleteComment(Long id, HttpServletRequest request);

    Result getCommentByUser(HttpServletRequest request);

    Result PostMessage(HttpServletRequest request, Comment comment);

    Result getMessage(HttpServletRequest request);
}
