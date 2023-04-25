package com.wounom.kaoyaniep.service;

import com.wounom.kaoyaniep.entity.Attentionlist;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.University;
import com.wounom.kaoyaniep.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/13 10:35
 */
public interface AttentionService {
    Result attention(HttpServletRequest request, Attentionlist attentionlist);

    Result getById(Long id);

    Result delete(HttpServletRequest request, Attentionlist attentionlist);

    Result getIf(HttpServletRequest request, Long targetid, Long schoolid);
}
