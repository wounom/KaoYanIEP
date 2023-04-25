package com.wounom.kaoyaniep.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wounom.kaoyaniep.entity.Collectlist;
import com.wounom.kaoyaniep.entity.Collectlistarticle;
import com.wounom.kaoyaniep.entity.Collectlistblock;
import com.wounom.kaoyaniep.entity.Collectlisttiewen;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/13 10:59
 */
@Mapper
public interface CollectMapper extends BaseMapper {

    @Delete("DELETE FROM collectlist WHERE id = #{id} and userId = #{userId}")
    int deleteByIdList(@Param("id") Long id,@Param("userId")Long userId);

    @Select("select * from collectlist where userId = #{userId} AND target = #{target}")
    List<Collectlist> getCollectlist(@Param("userId") Long userId,@Param("target") int target);

    int insertCollectlist(Collectlist collectlist);
    @Delete("delete from collectlist where userId = #{userId} AND id = #{id}")
    int deletet(Collectlist collectlist);

    Collectlist getCollectSingle(Collectlist collectlist);
}
