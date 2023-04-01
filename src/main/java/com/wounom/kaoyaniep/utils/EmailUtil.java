package com.wounom.kaoyaniep.utils;

import org.springframework.stereotype.Service;

/**
 * @author litind
 * @version 1.0
 * @date 2023/3/30 17:09
 */
@Service
public class EmailUtil {
    public String CreateCode(){
        String code = (Math.random() + "").substring(2, 8);
        return code;
    }
}
