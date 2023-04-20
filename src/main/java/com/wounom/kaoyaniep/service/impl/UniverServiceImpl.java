package com.wounom.kaoyaniep.service.impl;

import com.wounom.kaoyaniep.dao.UniverMapper;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.University;
import com.wounom.kaoyaniep.service.UniverService;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/6 13:45
 */
@Service
public class UniverServiceImpl implements UniverService {
    @Resource
    private UniverMapper univerMapper;
    @Override
    public Result getUniver(University university) {
        List<University> list = univerMapper.getUniverByCondition(university.getUniversityDistrict(),university.getUniversityHigherup(),university.getIfDouble()
                ,university.getIfGraduate(),university.getIfIndependent());
        if(list.size()>0){
            return  new Result(200,"获取成功",list.size(),list);
        }else {
            return new Result(400,"获取失败，无数据");
        }
    }
    /**
     *
     * 通过袁霞名称搜索院校
     * @param universityName
     * @return
     * @author litind
     **/
    @Override
    public Result getByName(String universityName) {
        University university = univerMapper.getByName(universityName);
        if (university!=null){
            return  new Result(200,"获取成功",1,university);
        }else {
            return new Result(400,"获取失败，无数据");
        }
    }
}
