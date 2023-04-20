package com.wounom.kaoyaniep.service;

import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.University;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/6 13:45
 */
public interface UniverService {
    Result getUniver(University university);

    Result getByName(String universityName);
}
