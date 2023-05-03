package com.wounom.kaoyaniep.service;


import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.Tiewen;

import javax.servlet.http.HttpServletRequest;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/3 9:18
 */
public interface TieWenService {


    Result collectTie(Tiewen tiewen, HttpServletRequest request);

    Result getTiewenByid(Long tiewenId);

    Result getTiewenByBlock(String blockName);

    Result PostTiewen(Tiewen tiewen, HttpServletRequest request);

    Result getTiewenByUserId(Long userId);

    Result deleteTiewen(HttpServletRequest request, Long tiewenId);

    Result getHot();
}
