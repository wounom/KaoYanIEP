package com.wounom.kaoyaniep.service;

import com.wounom.kaoyaniep.entity.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/7 8:22
 */
public interface BlockService {

    Result deleteBlock(String blockName);

    Result getBlock();

    Result collectBlock(String bName, HttpServletRequest request);
}
