package com.wounom.kaoyaniep.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wounom.kaoyaniep.entity.Admin;
import com.wounom.kaoyaniep.entity.FirstpagePush;
import com.wounom.kaoyaniep.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/2 9:56
 */
@Mapper
public interface AdminMapper extends BaseMapper {

    @Select("select * from admin where username = #{username}")
    Admin selectAdminByUsername(String email);//获取库中已有的管理员账号

    @Insert("insert into admin(username,password,salt)"  +
            "values (#{username},#{password},#{salt})")
    int insertAdmin(Admin admin);
    @Update("update admin set password = #{password},salt = #{salt} where username = #{username}")
    int updateAdmin(Admin admin);

    @Update("update firstpage_push set title = #{title},create_time = #{create_Time},URL = #{url}, image = #{image} where first_id = #{first_Id}")
    int updateFpp(FirstpagePush firstpagePush);

    @Update("update firstpage_push set title = null,create_time =null ,URL = null, image = null where first_id = #{first_Id}")
    int deleteFppById(int firstId);

    @Select("select * from firstpage_push where first_id = #{first_Id}")
    FirstpagePush selectFppById(int firstId);
}
