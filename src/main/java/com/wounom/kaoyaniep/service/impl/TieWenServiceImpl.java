package com.wounom.kaoyaniep.service.impl;



import com.wounom.kaoyaniep.dao.TieWenMapper;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.Tiewen;
import com.wounom.kaoyaniep.service.TieWenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/3 9:18
 */
@Service
public class TieWenServiceImpl implements TieWenService {
    @Resource
    private TieWenMapper tieWenMapper;

    /**
     *
     * 获取需要审核贴文
     * @return
     * @author litind
     **/
    @Override
    public Result getCheckTiewen() {
        List<Tiewen> needToCheck = tieWenMapper.getCheckTiewen();
        if (needToCheck.size()!=0){
            return new Result(200,"获取成功", needToCheck.size(), needToCheck);
        }else {
            return new Result(400,"暂无需要处理的贴文");
        }
    }

    /**
     *
     * 贴文审核
     * @param status
     * @return com.wounom.kaoyaniep.entity.Result
     * @author litind
     **/

    @Override
    public Result checkTiewen(int tiewenId,int status) {
        //拒绝为-1
        if(status <0){
           int r= tieWenMapper.updateTieWenStatus(tiewenId,status);
           if (r >0){
               return new Result(200,"拒绝成功");
           }else {
               return new Result(400,"系统错误");
           }
        }else {//过审为1
            int r= tieWenMapper.updateTieWenStatus(tiewenId,status);
            if (r >0){
                return new Result(200,"贴文已通过审核");
            }else {
                return new Result(400,"系统错误");
            }
        }
    }
}
