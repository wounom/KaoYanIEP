package com.wounom.kaoyaniep.service;

import com.wounom.kaoyaniep.entity.Result;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/4 9:47
 */
public interface PageService {
    Result getFPP();

    Result getOfficialTieByblockName(String blockName);

    Result getBlockByStatus(int status);

    Result getTiewenByBlock(String blockName);
}
