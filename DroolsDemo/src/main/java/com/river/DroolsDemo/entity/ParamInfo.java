package com.river.DroolsDemo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("re_param_info")
@Data
public class ParamInfo {

    @TableId("id")
    private Integer id ;
    private String operate ;
    private Integer paramA;
    private Integer paramB;
    private Integer result;
    private Date createTime ;

}
