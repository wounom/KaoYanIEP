package com.wounom.kaoyaniep.dao;

import com.wounom.kaoyaniep.entity.Block;
import com.wounom.kaoyaniep.entity.Collectlistblock;
import com.wounom.kaoyaniep.entity.Tiewen;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/7 8:21
 */
@Mapper
public interface BlockMapper {
    @Select("select * from block where district = #{district}")
    List<Block> getBlockByDistrict(String district);

    @Delete("delete from block where blockName = #{blockName}")
    int deleteBlock(String blockName);

    @Insert("insert into  collectlistblock(userEmail,collecttime,bName) values (#{userEmail},#{collecttime},#{bName})")
    int insertCollectlist(Collectlistblock collectlistblock);

    @Select("select * from tiewen where blockName = #{blockName} AND( title LIKE CONCAT('%',:keywors,'%') OR content LIKE CONCAT('%',:keywors,'%')  )")
    List<Tiewen> search(String blockName, String keywords);
}
