package com.wounom.kaoyaniep.dao;

import com.wounom.kaoyaniep.entity.Attentionlist;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/13 10:37
 */
@Mapper
public interface AttentionMapper {
    @Insert("insert into  attentionlist(target,targetid,Name,userId,schoolid)" +
            "values(#{target},#{targetid},#{name},#{userId},#{schoolid})")
    int addAttention(Attentionlist attentionlist);

    @Select("select * from attentionlist where userId = #{id}")
    List<Attentionlist> getByid(Long id);

    @Delete("delete from attentionlist where userId=#{userId}  AND schoolid=#{schoolid}")
    int deleteAttentionUniver(Attentionlist attentionlist);

    @Delete("delete from attentionlist where userId=#{userId}  AND targetid=#{targetid}" )
    int deleteAttentionUser(Attentionlist attentionlist);
}
