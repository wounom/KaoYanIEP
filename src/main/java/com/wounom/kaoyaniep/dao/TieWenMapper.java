package com.wounom.kaoyaniep.dao;



import com.wounom.kaoyaniep.entity.Collectlisttiewen;
import com.wounom.kaoyaniep.entity.Tiewen;
import org.apache.ibatis.annotations.*;

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

    @Insert("insert into  collectlisttiewen(userEmail,collecttime,tName,tid) values (#{userEmail},#{collecttime},#{tName}),#{tid}")
    int insertCollectlist(Collectlisttiewen collectlisttiewen);

    @Select("select * from where tiewenId = #{tiewenId}")
    List<Tiewen> getTiewenById(int tiewenId);

    @Select("select * from where blockName = #{blockName} ")
    List<Tiewen> getTiewenByBlock(String blockName);
}
