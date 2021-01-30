package com.river.DroolsDemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.river.DroolsDemo.entity.ParamInfo;
import com.river.DroolsDemo.mapper.ParamInfoMapper;
import com.river.DroolsDemo.service.ParamInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("paramInfoService")
public class ParamInfoServiceImpl extends ServiceImpl<ParamInfoMapper, ParamInfo> implements ParamInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParamInfoServiceImpl.class) ;

    @Resource
    private ParamInfoMapper paramInfoMapper ;

    @Override
    public ParamInfo selectById (String paramId){
        ParamInfo paramInfo = paramInfoMapper.selectById(paramId) ;
<<<<<<< HEAD
        LOGGER.info("ParamInfoServiceImpl-Sign：{}",paramInfo.getParamSign());
=======
        if(paramInfo != null) {
            LOGGER.info("ParamInfoServiceImpl-Operate：{}", paramInfo.getOperate());
        }
>>>>>>> 4e7d5be5383c4a2431cb4eacca62015123994f5c
        return paramInfo ;
    }

    @Override
    public void insertParam(ParamInfo paramInfo) {
        paramInfoMapper.insertParam(paramInfo) ;
    }

}
