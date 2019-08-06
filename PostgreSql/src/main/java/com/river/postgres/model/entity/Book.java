package com.river.postgres.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Accessors(chain = true)
@NoArgsConstructor
@Data
@ToString
@ApiModel(value="书籍信息")
public class Book implements Serializable {

    private static final long serialVersionUID = 8604990093149376515L;

    @ApiModelProperty(value="id", required = true)
    private Long id;

    @ApiModelProperty(value="书名", required = true)
    private String name;

    @ApiModelProperty(value="作者", required = true)
    private String author;

    @ApiModelProperty(value="价格", required = true)
    private Double price;

    @ApiModelProperty(value="主题描述")
    private String topic;

    @ApiModelProperty(value="出版日期", required = true)
    private Date publishDate;

    @ApiModelProperty(value="书店id", required = true)
    private Long bookStoreId;

}
