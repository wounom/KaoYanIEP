package com.wounom.kaoyaniep.dao;

import com.wounom.kaoyaniep.entity.FirstpagePush;
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
}
