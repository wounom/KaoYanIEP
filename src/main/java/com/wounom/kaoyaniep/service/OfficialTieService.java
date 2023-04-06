package com.wounom.kaoyaniep.service;

import com.wounom.kaoyaniep.entity.Result;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/6 12:49
 */
public interface OfficialTieService {
    Result getOfficialTieByblockName(String blockName);
}
