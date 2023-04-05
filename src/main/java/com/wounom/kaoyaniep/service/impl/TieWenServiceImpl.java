package com.wounom.kaoyaniep.service.impl;

import com.wounom.kaoyaniep.dao.TieWenMapper;
import com.wounom.kaoyaniep.dao.UserMapper;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.Tiewen;
import com.wounom.kaoyaniep.service.TieWenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.geom.RectangularShape;
import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/3 9:18
 */
@Service
public class TieWenServiceImpl implements TieWenService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private TieWenMapper tieWenMapper;


}
