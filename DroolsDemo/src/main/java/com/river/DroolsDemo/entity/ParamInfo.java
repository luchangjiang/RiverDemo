package com.river.DroolsDemo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
<<<<<<< HEAD
=======
import lombok.Data;
>>>>>>> 4e7d5be5383c4a2431cb4eacca62015123994f5c

import java.util.Date;

@TableName("re_param_info")
<<<<<<< HEAD
public class ParamInfo {

    @TableId("id")
    private String id ;
    private String paramSign ;
    private Date createTime ;
    private Date updateTime ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParamSign() {
        return paramSign;
    }

    public void setParamSign(String paramSign) {
        this.paramSign = paramSign;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
=======
@Data
public class ParamInfo {

    @TableId("id")
    private Integer id ;
    private String operate ;
    private Integer paramA;
    private Integer paramB;
    private Integer result;
    private Date createTime ;

>>>>>>> 4e7d5be5383c4a2431cb4eacca62015123994f5c
}
