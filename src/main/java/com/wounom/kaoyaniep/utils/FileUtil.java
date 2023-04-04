package com.wounom.kaoyaniep.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiListUI;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/2 19:56
 */
public class FileUtil {
    /**
     *
     * 保存文件
     * @param file
     * @param path
     * @return java.lang.String
     * @author litind
     **/
    public static String saveFile(MultipartFile file,String path) throws IOException {
        String fileName = file.getOriginalFilename();
        String filesub = fileName.substring(fileName.indexOf("."));
        String newFileName = UUID.randomUUID().toString().replace("-","")+filesub;
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdir();
        }
        File file1 = new File(path,newFileName);
        file.transferTo(file1);
        return newFileName;
    }
    /**
     *
     * 删除无效文件
     * @param path
     * @return
     * @author litind
     **/
    public static boolean deleteFile(String path){
        boolean flag = false;
        File file = new File(path);
        if (file.isFile()&& file.exists()){
            flag =  file.delete();
        }
        return flag;
    }
}
