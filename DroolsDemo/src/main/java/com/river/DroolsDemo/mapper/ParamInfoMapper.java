package com.river.DroolsDemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.river.DroolsDemo.entity.ParamInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParamInfoMapper extends BaseMapper<ParamInfo> {
    void insertParam(ParamInfo paramInfo) ;
}
