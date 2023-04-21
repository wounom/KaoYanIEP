package com.wounom.kaoyaniep.dao;

import com.wounom.kaoyaniep.entity.University;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/2 22:30
 */
@Mapper
public interface UniverMapper {
    List<University> getUniverByCondition(University university);

    University getByName(String universityName);
}
