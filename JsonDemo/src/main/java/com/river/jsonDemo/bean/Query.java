package com.river.jsonDemo.bean;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-07-30 10:51
 **/

import lombok.Data;
import org.apache.commons.collections4.map.LinkedMap;

import java.util.List;

@Data
public class Query {
    private String id;
    private String key;
    private String tableName;
    private String className;
    private List<LinkedMap<String, Object>> column;
    private List<Column> columnList;
}
