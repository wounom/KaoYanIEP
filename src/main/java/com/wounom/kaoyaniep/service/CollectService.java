package com.wounom.kaoyaniep.service;

import com.wounom.kaoyaniep.entity.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/13 10:56
 */
public interface CollectService {
    Result getCollectlistArticle(HttpServletRequest request);

    Result getCollectlistBlock(HttpServletRequest request);

    Result getCollectlistTiewen(HttpServletRequest request);

    Result deleteArticleById(Long aid, HttpServletRequest request);

    Result deleteTiewenById(Long tid, HttpServletRequest request);

    Result deleteBlockById(String bName, HttpServletRequest request);
}
