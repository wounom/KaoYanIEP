package com.wounom.kaoyaniep.dao;

import com.wounom.kaoyaniep.entity.Block;
import org.apache.ibatis.annotations.Delete;
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
    @Select("select * from block")
    List<Block> getBlock();

    @Delete("delete from block where blockName = #{blockName}")
    int deleteBlock(String blockName);
}
