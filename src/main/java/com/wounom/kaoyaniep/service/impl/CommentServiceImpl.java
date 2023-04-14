package com.wounom.kaoyaniep.service.impl;

import cn.hutool.core.date.DateTime;
import com.wounom.kaoyaniep.dao.CommentMapper;
import com.wounom.kaoyaniep.entity.Comment;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.User;
import com.wounom.kaoyaniep.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/14 16:35
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    public Result postComment(Comment comment, HttpServletRequest request) {
        //todo:增加贴文不存在与评论不存在的异常判断
        if (comment.getContent()==null){
            return new Result(400,"数据为空");
        }
        User user = (User) request.getSession().getAttribute("user");
        comment.setUserId(user.getId());
        comment.setUserName(user.getUsername());
        comment.setUserImg(user.getImage());
        comment.setCreateTime(DateTime.now());
        int r= commentMapper.save(comment);
        return new Result(400,"评论成功");
    }

    /**
     *
     * 获取评论
     * @param tiewenId
     * @return
     * @author litind
     **/
    @Override
    public Result getComment(Long tiewenId) {
        List<Comment> comments = commentMapper.getByTiewenId(tiewenId);
        List<Comment> rootComments = comments.stream().filter(comment -> comment.getParentId()==null).collect(Collectors.toList());
        for (Comment rootComment : rootComments){
            rootComment.setChildren(comments.stream().filter(comment -> rootComment.getId().equals(comment.getParentId())).collect(Collectors.toList()));
        }
        return new Result(200,"success",rootComments.size(),rootComments);
    }
}
