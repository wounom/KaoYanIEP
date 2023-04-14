package com.wounom.kaoyaniep.service.impl;

import cn.hutool.core.date.DateTime;
import com.wounom.kaoyaniep.dao.CommentMapper;
import com.wounom.kaoyaniep.dao.TieWenMapper;
import com.wounom.kaoyaniep.entity.Comment;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.Tiewen;
import com.wounom.kaoyaniep.entity.User;
import com.wounom.kaoyaniep.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
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
    @Resource
    private TieWenMapper tieWenMapper;

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

    /**
     *
     * 删除评论
     * @param request,id
     * @return
     * @author litind
     **/
    @Override
    public Result deleteComment(Long id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Comment comment = new Comment();
        comment.setId(id);
        comment.setUserId(user.getId());
        int r = commentMapper.deleteComment(comment);
        if(r>0){
            return new Result(200,"删除成功");
        }else {
            return new Result(400,"删除失败");
        }
    }

    /**
     * 用户获取自己帖子下的评论
     *
     * @param request
     * @return
     * @author litind
     **/
    @Override
    public Result getCommentByUser(HttpServletRequest request) {
        User user =(User) request.getSession().getAttribute("user");
        Long userId = user.getId();
        //先获取用户的贴文
        List<Tiewen> tiewenList = tieWenMapper.getTieWenbyUserId(userId);
        //再通过用户贴文的id获取评论
        List<List<Comment>> comments = (List<List<Comment>>) new Comment();
        for (int i = 0 ; i<tiewenList.size();i++){
            List<Comment> list = commentMapper.getByTiewenId(tiewenList.get(i).getTiewenId());
            comments.add(list);
        }
        return new Result(200,"获取成功",comments.size(),comments);
    }
}
