package com.wounom.kaoyaniep.dao;

import com.wounom.kaoyaniep.entity.Block;
import com.wounom.kaoyaniep.entity.Collectlistblock;
import com.wounom.kaoyaniep.entity.Tiewen;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/7 8:21
 */
@Mapper
public interface BlockMapper {
    @Select("select * from block where status = #{status}")
    List<Block> getBlockByStatus(int status);

    @Delete("delete from block where blockName = #{blockName}")
    int deleteBlock(String blockName);

    @Insert("insert into  collectlistblock(userEmail,collecttime,bName) values (#{userEmail},#{collecttime},#{bName})")
    int insertCollectlist(Collectlistblock collectlistblock);

    @Select("select * from tiewen where blockName = #{blockName} AND ( title LIKE CONCAT('%',#{keywords},'%') OR content LIKE CONCAT('%',#{keywords},'%')  )")
    List<Tiewen> search(@Param("blockName")String blockName,@Param("keywords") String keywords);

    @Update("update block set tiewencount = tiewencount+1  where blockName=#{blockName}")
    void addTiewenCount(String blockName);
    @Update("update block set tiewencount = tiewencount+1  where blockName=#{blockName}")
    void cutTiewenCount(String blockName);
}
