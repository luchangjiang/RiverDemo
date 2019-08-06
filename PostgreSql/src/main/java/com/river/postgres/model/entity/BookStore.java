package com.river.postgres.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Accessors(chain = true)
@NoArgsConstructor
@Data
@ToString
@ApiModel(value="书店信息")
public class BookStore implements Serializable {

    private static final long serialVersionUID = 1183385713216587274L;

    @ApiModelProperty(value="id", required = true)
    private long id;

    @ApiModelProperty(value="书店名称", required = true)
    private String name;

    @ApiModelProperty(value="书店地址")
    private String address;

}
