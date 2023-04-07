package com.wounom.kaoyaniep.service;


import com.wounom.kaoyaniep.entity.Result;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/3 9:18
 */
public interface TieWenService {

    Result getCheckTiewen();

    Result checkTiewen(int tiewenId, int status);

    Result getTiewenByUser(int userId);

    Result deleteTiewenByBlock(String blockName);
}
