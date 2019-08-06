package com.river.postgres.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Xiaoyue Xiao
 */
@Accessors(chain = true)
@NoArgsConstructor
@Data
@ToString
@ApiModel(value="错误信息")
public class PaginatedResult implements Serializable {

    private static final long serialVersionUID = 6191745064790884707L;

    @ApiModelProperty(value="当前页", required = true)
    private int currentPage;

    @ApiModelProperty(value="总页数", required = true)
    private int totalPage;

    @ApiModelProperty(value="数据", required = true)
    private Object data;

}
