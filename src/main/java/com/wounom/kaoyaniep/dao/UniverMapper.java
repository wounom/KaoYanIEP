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

    @Select("<script>select *  from university "+
            "where 1"+
            "            <if test=\"universityDistrict!=null\"> universityDistrict= #{universityDistrict}, </if>"+
            "            <if test=\"universityHigherup != null\"> universityHigherup = #{universityHigherup}, </if>" +
            "            <if test=\"ifDouble!= null\"> ifDouble = #{ifDouble},</if>" +
            "            <if test=\"ifGraduate != null\"> ifGraduate = #{ifGraduate},</if>" +
            "            <if test=\"ifIndependent != null\"> ifIndependent = #{ifIndependent}</if>"+
            "            </script>")
    List<University> getUniverByCondition(@Param("universityDistrict") String universityDistrict,@Param("universityHigherup") String universityHigherup,
                                          @Param("ifDouble") Integer ifDouble,@Param("ifGraduate") Integer ifGraduate,@Param("ifIndependent") Integer ifIndependent);

    @Select("select * from university where universityName = #{universityName}")
    University getByName(String universityName);
}
