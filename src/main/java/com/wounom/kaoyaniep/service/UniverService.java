package com.wounom.kaoyaniep.service;

import com.wounom.kaoyaniep.entity.Result;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/6 13:45
 */
public interface UniverService {
    Result getUniver(String universityDistrict, String universityHigherup, Integer ifDouble, Integer ifGraduate, Integer ifIndependent);
}
