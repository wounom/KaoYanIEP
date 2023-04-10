package com.wounom.kaoyaniep.dao;

import com.wounom.kaoyaniep.entity.Collectlistarticle;
import com.wounom.kaoyaniep.entity.TiewenOfficial;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/6 12:51
 */

@Mapper
public interface OfficialTieMapper {
    @Select("select * from tiewen_official where blockName = #{blockName}")
    List<TiewenOfficial> getOfficialTieByBlockName(String blockName);

    @Select("select * from tiewen_official order by createTime desc limit 0,9")
    List<TiewenOfficial> getOfficialTieByTime();

    @Insert("insert into  collectlistarticle(userEmail,collecttime,aName,aid) values (#{userEmail},#{collecttime},#{aName},#{aid})")
    int insertCollectlist(Collectlistarticle collectlistarticle);

    @Select("select * from tiewen_official where tiewenID = #{tiewenId}")
    List<TiewenOfficial> getOfficialTieById(int tiewenId);
}
