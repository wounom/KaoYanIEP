package com.wounom.kaoyaniep.dao;

import com.wounom.kaoyaniep.controller.CommentController;
import com.wounom.kaoyaniep.entity.Comment;
import com.wounom.kaoyaniep.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/14 16:35
 */
@Mapper
public interface CommentMapper {
    @Insert("insert into comment(userId,userName,userImg,content,tiewenId,createTime,parentId" +
            ") values (#{userId},#{userName},#{userImg},#{content},#{tiewenId},#{createTime},#{parentId}) ")
    int save(Comment comment);
    @Select("select * from comment where tiewenId = #{tiewenId}")
    List<Comment> getByTiewenId(Long tiewenId);
    List<Comment> findByTiewenId(Long tiewenId);
    @Delete("delete from comment where id = #{id}")
    int deleteComment(Long id);
    int saveMessage(Comment comment);
    @Select("select * from comment where targetUserId = #{targetUserId}")
    List<Comment> getMessage(Long targetUserId);
    @Select("SELECT userName, createTime  FROM comment  WHERE tiewenId = #{tiewenId} AND parentId is Null  ORDER BY createTime DESC LIMIT 1")
    Comment getLastBytiewenId(Long tiewenId);

    @Update("update comment  set userName = #{username}  where userId=#{id}")
    int updateUsername(User newuser);

    List<Comment> getByUser(Long id);

    List<Comment> getChlByid(Long id);

    String getTiewenTitle(Long tiewenId);

    void updateImg(User user);

    List<Comment> findByParentId(Long id);
}
