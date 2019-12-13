package com.river.ApplicationStartedDemo;

import java.util.Date;

public class ParamInfo {

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
}
