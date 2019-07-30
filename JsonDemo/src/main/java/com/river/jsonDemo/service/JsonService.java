package com.river.jsonDemo.service;

import com.alibaba.fastjson.JSON;
import com.river.jsonDemo.bean.Column;
import com.river.jsonDemo.bean.Query;
import com.river.jsonDemo.util.MapUtil;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-07-30 11:10
 **/
@Service("jsonService")
public class JsonService {
    /**
     * 读取类路径下的配置文件
     * 解析成对象数组并返回
     * @throws IOException
     */
    public List<Query> test() throws IOException {
        // 读取类路径下的query.json文件
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream inputStream = cl.getResourceAsStream("query.json");
        String jsontext = IOUtils.toString(inputStream, "utf8");
        // 先将字符jie串转为List数组
        List<Query> queryList = JSON.parseArray(jsontext, Query.class);
        for (Query query : queryList) {
            List<Column> columnList = new ArrayList<Column>();
            List<LinkedMap<String,Object>> columns = query.getColumn();
            for (LinkedMap<String, Object> linkedMap : columns) {
                //将map转化为java实体类
                Column column = (Column) MapUtil.map2Object(linkedMap, Column.class);
                System.out.println(column.toString());
                columnList.add(column);
            }
            query.setColumnList(columnList); //为columnList属性赋值
        }
        return queryList;
    }
}
