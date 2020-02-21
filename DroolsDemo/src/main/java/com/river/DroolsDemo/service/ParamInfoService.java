package com.river.DroolsDemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.river.DroolsDemo.entity.ParamInfo;

public interface ParamInfoService extends IService<ParamInfo> {
    ParamInfo selectById(String paramId) ;
    void insertParam(ParamInfo paramInfo) ;
}
