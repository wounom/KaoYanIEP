package com.wounom.kaoyaniep.service;

import com.wounom.kaoyaniep.entity.Collectlist;
import com.wounom.kaoyaniep.entity.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/13 10:56
 */
public interface CollectService {


    Result deleteList(List<Long> id, HttpServletRequest request);

    Result getCollectlist(HttpServletRequest request, int target);

    Result collect(Collectlist collectlist, HttpServletRequest request);

    Result delete(Long id, HttpServletRequest request);

    Result getCollectSingle(HttpServletRequest request, Collectlist collectlist);
}
