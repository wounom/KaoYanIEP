package com.wounom.kaoyaniep.dao;

import com.wounom.kaoyaniep.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/4 9:46
 */
@Mapper
public interface PageMapper {
    @Select("select * from firstpage_push")
    List<FirstpagePush> selectFpp();
    @Select("select * from tiewen_official where blockName = #{blockName}")
    List<TiewenOfficial> getOfficialTieByBlockName(String blockName);
    @Select("select * from block where status = #{status}")
    List<Block> getBlockByStatus(int status);


}
