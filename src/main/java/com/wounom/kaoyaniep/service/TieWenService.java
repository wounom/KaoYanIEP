package com.wounom.kaoyaniep.service;


import com.wounom.kaoyaniep.entity.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/3 9:18
 */
public interface TieWenService {


    Result collectTie(String tName, HttpServletRequest request);
}
