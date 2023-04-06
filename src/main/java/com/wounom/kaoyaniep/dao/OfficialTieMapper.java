package com.wounom.kaoyaniep.dao;

import com.wounom.kaoyaniep.entity.TiewenOfficial;
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
}