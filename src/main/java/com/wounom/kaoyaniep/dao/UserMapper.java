package com.wounom.kaoyaniep.dao;

import com.wounom.kaoyaniep.entity.*;
import com.wounom.kaoyaniep.entity.Result;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/3/30 14:39
 */
@Mapper
@Repository
public interface UserMapper  {
    /**
     *
     * 根据用户名称查询用户
     * @param username
     * @return com.wounom.kaoyaniep.entity.User
     * @author litind
     **/

    User selectByUsername(@Param("username") String username);
    int countAll(String search);

    List<User> getUsers(String search);

    @Update("<script>"+"update user"+"<set>"+
            "<if test=\"username!=null\"> username= #{username}, </if>"+
            "<if test=\"sex != null\"> sex = #{sex}, </if>"+
            "<if test=\"birthDay!= null\"> birthday = #{birthDay},</if>"+
            "<if test=\"target_Unversity != null\"> target_unversity = #{target_Unversity},</if>"
            +"</set>"+"where email = #{email}"+"</script>")
    int updateUser(User user);

    User getUserById(Integer id);

    void deleteById(Integer id);

    void insert(User user);

    @Select("select * from user where email = #{email} AND is_valid=1")
    User selectByUserEmail1(String email);//获取已经注册的账号

    @Select(" select * from user where email = #{email}")
    User selectUserByEmail_(String email);//获取该邮箱的所有账号

    @Update("update user set username = #{username}, is_valid = #{is_Valid} , salt = #{salt}, password = #{password} where email = #{email}")
    void updateregist(User user);

    @Insert("insert into  user(email,code,active_time)\n" +
            "values (#{email},#{code},#{active_Time})")
    void insertCode(User user);

    @Update("update  user set code =#{code}, active_time=#{active_Time} where email=#{email}")
    void updateUsercode(User user);

    @Select("Select * FROM user")
    List<User>  findall();
    @Select("Select username,email,password,salt,is_valid FROM user")
    List<User> selectList();
    @Select("SELECT code from user where email = #{email}")
    String findCode(String email);

    @Select("select active_time from user where email = #{email}")
    LocalDateTime findActive(String email);

    @Update("update user set code =#{code}, active_time=#{active_Time},salt = #{salt}, password = #{password} where email = #{email}")
    void updatePw(User user);

    @Update("update user set image = #{image}, imagepath = #{imagePath} where email = #{email}")
    void updateUserImg(User user);




}
