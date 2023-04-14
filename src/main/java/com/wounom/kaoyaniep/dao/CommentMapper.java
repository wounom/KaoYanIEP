package com.wounom.kaoyaniep.dao;

import com.wounom.kaoyaniep.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/14 16:35
 */
@Mapper
public interface CommentMapper {
    @Insert("insert into comment(userId,userName,userImg,content,tiewenId,createTime,parentId,rootId" +
            ") values (#{userId},#{userName},#{userImg},#{content},#{tiewenId},#{createTime},#{parentId},#{rootId}) ")
    int save(Comment comment);
    @Select("select * from comment where tiewenId = #{tiewenId}")
    List<Comment> getByTiewenId(Long tiewenId);
}
