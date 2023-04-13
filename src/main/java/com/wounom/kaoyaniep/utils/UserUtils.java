package com.wounom.kaoyaniep.utils;

import cn.hutool.core.util.IdUtil;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/13 9:51
 */
public class UserUtils {
    /**
     *
     * 生成随机用户名
     * @param
     * @return
     * @author litind
     **/
    //自动生成名字（中文）
    public static String getRandomChineseString(){
        String zh_cn = "";
        String str ="";
        // Unicode中汉字所占区域\u4e00-\u9fa5,将4e00和9fa5转为10进制
        int start = Integer.parseInt("4e00", 16);
        int end = Integer.parseInt("9fa5", 16);
        for(int ic=0;ic<4;ic++){
            // 随机值
            int code = (new Random()).nextInt(end - start + 1) + start;
            // 转字符
            str = new String(new char[] { (char) code });
            zh_cn=zh_cn+str;
        }
        return zh_cn;
    }

}
