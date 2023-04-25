package com.wounom.kaoyaniep.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.api.R;
import com.wounom.kaoyaniep.dao.CollectMapper;
import com.wounom.kaoyaniep.entity.*;
import com.wounom.kaoyaniep.service.CollectService;
import com.wounom.kaoyaniep.utils.TokenUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/13 10:56
 */
@Service
public class CollectServiceImpl implements CollectService {
    @Resource
    private CollectMapper collectMapper;
    /**
     *
     * 获取单个的收藏
     * @param request,collectlist
     * @return
     * @author litind
     **/
    @Override
    public Result getCollectSingle(HttpServletRequest request, Collectlist collectlist) {
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
        Long userId = user.getId();
        collectlist.setUserId(userId);
        Collectlist collectResult = collectMapper.getCollectSingle(collectlist);
        if (collectResult!=null){
            return new Result(200,"获取成功",1,collectResult);
        }else {
            return new Result(400,"获取失败，未收藏");
        }
    }

    /**
     *
     * 获取用户收藏列表
     * @param request,target
     * @return
     * @author litind
     **/
    @Override
    public Result getCollectlist(HttpServletRequest request, int target) {
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
        Long userId = user.getId();
        List<Collectlist> list =  collectMapper.getCollectlist(userId,target);
        if (list.size()>0){
            return new Result(200,"获取成功", list.size(),list);
        }else {
            return new Result(400,"数据为空");
        }
    }
    /**
     *
     * 收藏操作
     * @param collectlist,request
     * @return
     * @author litind
     **/
    @Override
    public Result collect(Collectlist collectlist, HttpServletRequest request) {
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
        collectlist.setUserId(user.getId());
        collectlist.setCollecttime(DateTime.now());
        if( collectMapper.getCollectSingle(collectlist)!=null){
            return new Result(400,"已收藏");
        }else {
            int r = collectMapper.insertCollectlist(collectlist);
            if (r>0){
                return new Result(200,"收藏成功");
            }else {
                return new Result(400,"收藏失败");
            }
        }
    }

    /**
     *
     * 删除收藏
     * @param id,request
     * @return
     * @author litind
     **/
    @Override
    public Result delete(Long id, HttpServletRequest request) {
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
        Collectlist collectlist = new Collectlist();
        collectlist.setUserId(user.getId());
        collectlist.setId(id);
        int r = collectMapper.deletet(collectlist);
        if (r>0){
            return new Result(200,"删除成功");
        }else {
            return new Result(400,"删除失败");
        }
    }

    /**
     *
     * 批量删除
     * @param id
     * @return
     * @author litind
     **/
    @Override
    public Result deleteList(List<Long> id,HttpServletRequest request) {
        String token = request.getHeader("token");
        User user = TokenUtils.getUser(token);
        Long userId = user.getId();
        int r =0 ;
        for (int i=0;i<id.size();i++){
            r +=collectMapper.deleteByIdList(id.get(i),userId);
        }
        if (r>0){
            return new Result(200,"删除成功");
        }else {
            return new Result(400,"删除失败");
        }
    }
}
