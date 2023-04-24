package com.wounom.kaoyaniep.dao;

import com.wounom.kaoyaniep.entity.User;
import io.swagger.v3.oas.annotations.Parameter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/24 9:55
 */

@Mapper
public interface UtilsMapper {

    /**
     *
     * token登陆时查询token用户
     * @param id
     * @return
     * @author litind
     **/
    User findUserById(@Param("id") int id);

}
