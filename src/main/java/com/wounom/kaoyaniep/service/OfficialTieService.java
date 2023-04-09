package com.wounom.kaoyaniep.service;

import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.TiewenOfficial;

import javax.servlet.http.HttpServletRequest;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/6 12:49
 */
public interface OfficialTieService {
    Result getOfficialTieByblockName(String blockName);

    Result getOfficialTieByTime();

    Result collectArticle(TiewenOfficial tiewenOfficial, HttpServletRequest request);

    Result getofficialTieById(int tiewenId);
}
