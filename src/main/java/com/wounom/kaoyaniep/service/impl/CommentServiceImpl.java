package com.wounom.kaoyaniep.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.plugins.Page;
import com.wounom.kaoyaniep.dao.CommentMapper;
import com.wounom.kaoyaniep.dao.TieWenMapper;
import com.wounom.kaoyaniep.dao.UniverMapper;
import com.wounom.kaoyaniep.dao.UserMapper;
import com.wounom.kaoyaniep.entity.Comment;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.Tiewen;
import com.wounom.kaoyaniep.entity.User;
import com.wounom.kaoyaniep.service.CommentService;

import com.wounom.kaoyaniep.utils.TokenUtils;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
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
    @Resource
    private TieWenMapper tieWenMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public Result postComment(Comment comment, HttpServletRequest request) {
        //todo:增加贴文不存在与评论不存在的异常判断
        if (comment.getContent()==null){
            return new Result(400,"数据为空");
        }
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
        comment.setUserId(user.getId());
        comment.setUserName(user.getUsername());
        comment.setUserImg(user.getImage());
        comment.setCreateTime(DateTime.now());
        tieWenMapper.updateCommentCount(comment.getTiewenId());
        int r= commentMapper.save(comment);
        if (r>0){
            return new Result(200,"评论成功");
        }else {
            return new Result(400,"失败");
        }
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
        if (rootComments.size()>0){
            return new Result(200,"success",rootComments.size(),rootComments);
        }else {
            return new Result(400,"数据空");
        }

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
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
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
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
        Long userId = user.getId();
        //先获取用户的贴文
        List<Tiewen> tiewenList = tieWenMapper.getTieWenbyUserId(userId);
        //再通过用户贴文的id获取评论
        //查询出用户所有贴文下的评论
        List<Comment> comments = new ArrayList<Comment>();
        for (int i = 0 ; i<tiewenList.size() ;i++){
            List<Comment> c = commentMapper.getByTiewenId(tiewenList.get(i).getTiewenId());
            for (int j=0;j<c.size();j++){
                comments.add(c.get(j));
            }
        }
        System.out.println("评论数:"+comments.size());
        //放入树
        List<Comment> rootComments = comments.stream().filter(comment -> comment.getParentId()==null).collect(Collectors.toList());
        for (Comment rootComment : rootComments){
            rootComment.setChildren(comments.stream().filter(comment -> rootComment.getId().equals(comment.getParentId())).collect(Collectors.toList()));
        }
        System.out.println(rootComments.size());
        return new Result(200,"获取成功",comments.size(),comments);
    }
    /**
     *
     * 发送留言
     * @param request,comment
     * @return
     * @author litind
     **/
    @Override
    public Result PostMessage(HttpServletRequest request, Comment comment) {
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
        comment.setUserId(user.getId());
        comment.setUserName(user.getUsername());
        comment.setUserImg(user.getImagepath());
        comment.setCreateTime(DateTime.now());
        Long uid = user.getId();
        User user1 = userMapper.getUserById(comment.getTargetUserId());
        if (user1==null){
            return new Result(400,"目标用户不存在");
        }
        int r= commentMapper.saveMessage(comment);
        if (r>0){
            return new Result(200,"成功");
        }else {
            return new Result(400,"失败");
        }
    }

    /**
     *
     * 获取给自己的留言
     * @param request
     * @return
     * @author litind
     **/
    @Override
    public Result getMessage(HttpServletRequest request) {
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
        Long targetUserId = user.getId();
        List<Comment> commentList = commentMapper.getMessage(targetUserId);
        if (commentList.isEmpty()){
            return new Result(400,"无留言");
        }else {
            return new Result(200,"成功",commentList.size(),commentList);
        }
    }
}
