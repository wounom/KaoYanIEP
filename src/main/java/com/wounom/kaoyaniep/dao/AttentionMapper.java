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
    @Insert("insert into  attentionlist(target,targetid,Name,id,schoolid)" +
            "values (#{target},#{targetId},#{Name},#{id},#{schoolId})")
    int addAttention(Attentionlist attentionlist);

    @Select("select * from attentionlist where id = #{id}")
    List<Attentionlist> getByid(Long id);

    @Delete("delete from attentionlist where id=#{id} AND targetid=#{targetId} AND schoolid = #{schoolId}")
    int delete(Attentionlist attentionlist);
}
