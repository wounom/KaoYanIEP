package com.wounom.kaoyaniep.dao;

import com.wounom.kaoyaniep.entity.Tiewen;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/3 9:18
 */
@Mapper
public interface TieWenMapper {
    @Select("select * from tiewen where user_id = #{userId}")
    List<Tiewen> getTieWenbyUserId(long id);
}
