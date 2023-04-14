package com.wounom.kaoyaniep.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wounom.kaoyaniep.entity.Collectlist;
import com.wounom.kaoyaniep.entity.Collectlistarticle;
import com.wounom.kaoyaniep.entity.Collectlistblock;
import com.wounom.kaoyaniep.entity.Collectlisttiewen;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/13 10:59
 */
@Mapper
public interface CollectMapper extends BaseMapper {
    @Select("select * from collectlistarticle where userEmail = #{userEmail}")
    List<Collectlistarticle> getCollectlistA(String userEmail);
    @Select("select * from collectlistblock where userEmail = #{userEmail}")
    List<Collectlistblock> getCollectlistB(String userEmail);

    @Select("select * from collectlisttiewen where userEmail = #{userEmail}")
    List<Collectlisttiewen> getCollectlistT(String userEmail);


    @Delete("delete from collectlistarticle where userEmail = #{userEmail} and aid = #{aid}")
    int deleteCollectArticleByid(Collectlistarticle collectlistarticle);
    @Delete("delete from collectlisttiewen where userEmail = #{userEmail} and tid = #{tid}")
    int deleteCollectTiewenByid(Collectlisttiewen collectlisttiewen);
    @Delete("delete from collectlistblock where userEmail = #{userEmail} and bName = #{bName}")
    int deleteCollectBlockByid(Collectlistblock collectlistblock);

    @Delete("DELETE FROM collectlist WHERE id = #{id} and userId = #{userId}")
    int deleteByIdList(@Param("id") Long id,@Param("userId")Long userId);


    @Select("select * from collectlist where userId = #{userId} AND target = #{target}")
    List<Collectlist> getCollectlist(@Param("userId") Long userId,@Param("target") int target);

    @Insert("insert into  collectlist(userId,collecttime,target,cName,tid) values (#{userId},#{collecttime},#{target},#{cName}),#{tid}")
    int insertCollectlist(Collectlist collectlist);
    @Delete("delete from collectlist where userId = #{userId} AND target = #{target} AND tid = #{tid}")
    int deletet(Collectlist collectlist);
}
