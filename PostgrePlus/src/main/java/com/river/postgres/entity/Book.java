/*
 *    Copyright (c) 2018-2025, Eddid All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: River (River.lu@newtype.io)
 */
package com.river.postgres.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
<<<<<<< HEAD
import com.river.postgres.annotation.ShowLog;
=======
>>>>>>> 4e7d5be5383c4a2431cb4eacca62015123994f5c
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
=======
>>>>>>> 4e7d5be5383c4a2431cb4eacca62015123994f5c

import java.util.Date;

/**
 * dfd
 *
 * @author River
 * @date 2019-08-07 10:30:37
 */
@Data
@TableName("book")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="书籍信息")
public class Book extends Model<Book> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="id")
    @TableId
    private Integer id;
    @ApiModelProperty(value="书名")
    private String bookName;

    @ApiModelProperty(value="作者")
    private String author;

    @ApiModelProperty(value="价格")
    private Double price;

    @ApiModelProperty(value="主题描述")
    private String topic;

    @ApiModelProperty(value="出版日期")
    private Date publishDate;

    @ApiModelProperty(value="创建时间")
    private Date createTime;

    @ApiModelProperty(value="书店id")
    private Long bookStoreId;
<<<<<<< HEAD

    @ShowLog
    public String getAuthor() {
        return author;
    }
=======
>>>>>>> 4e7d5be5383c4a2431cb4eacca62015123994f5c
}
