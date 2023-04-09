package com.wounom.kaoyaniep.service;

import com.wounom.kaoyaniep.entity.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/6 12:49
 */
public interface OfficialTieService {
    Result getOfficialTieByblockName(String blockName);

    Result getOfficialTieByTime();

    Result collectArticle(String aName, HttpServletRequest request);
}
