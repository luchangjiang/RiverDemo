package com.river.jsonDemo.bean;

import lombok.Data;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-07-30 10:50
 **/
@Data
public class Column {
    private String key;
    private String header;
    private String width;
    private Boolean allowSort;
    private Boolean hidden;
}
