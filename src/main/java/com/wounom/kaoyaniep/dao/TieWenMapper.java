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
    @Select("select * from tiewen where userId = #{userId}")
    List<Tiewen> getTieWenbyUserId(Long userId);


    @Select("select * from tiewen where status = 0")
    List<Tiewen> getCheckTiewen();


    @Delete("delete from tiewen where blockName = #{blockName}")
    int deleteTiewenByBlock(String blockName);

    @Insert("insert into  collectlisttiewen(userEmail,collecttime,tName,tid) values (#{userEmail},#{collecttime},#{tName}),#{tid}")
    int insertCollectlist(Collectlisttiewen collectlisttiewen);

    @Select("select * from where tiewenId = #{tiewenId}")
    List<Tiewen> getTiewenById(int tiewenId);

    @Select("select * from tiewen where blockName = #{blockName} order by createTime desc")
    List<Tiewen> getTiewenByBlock(String blockName);

    @Insert("insert into tiewen(userId,title,createTime,content,blockName) values (#{userId},#{title},#{createTime},#{content},#{blockName})")
    int PostTiewen(Tiewen tiewen);

    @Delete("delete from tiewen where tiewenId = #{tiewenId} AND userId = #{userId}")
    int deleteTiewenByid(Tiewen tiewen);
    @Update("update tiewen  set commentCount = commentCount+1  where tiewenId=#{tiewenId}")
    void updateCommentCount(Long tiewenId);

    @Select("select * from tiewen order by commentCount limit 0,9")
    List<Tiewen> getHot();
    @Select("select * from tiewen order by createTime limit 0,9")
    List<Tiewen> getNew();
}
