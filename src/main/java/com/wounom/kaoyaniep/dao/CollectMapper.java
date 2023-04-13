package com.wounom.kaoyaniep.dao;

import com.wounom.kaoyaniep.entity.Collectlistarticle;
import com.wounom.kaoyaniep.entity.Collectlistblock;
import com.wounom.kaoyaniep.entity.Collectlisttiewen;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/13 10:59
 */
@Mapper
public interface CollectMapper {
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
}
