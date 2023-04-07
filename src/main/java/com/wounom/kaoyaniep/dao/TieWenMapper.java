package com.wounom.kaoyaniep.dao;



import com.wounom.kaoyaniep.entity.Tiewen;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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


    @Select("select * from tiewen where status = 0")
    List<Tiewen> getCheckTiewen();

    @Update("update tiewen set status = #{status} where tiewenId = #{tiewenId}")
    int updateTieWenStatus(int tiewenId, int status);

    @Delete("delete from tiewen where blockName = #{blockName}")
    int deleteTiewenByBlock(String blockName);
}
