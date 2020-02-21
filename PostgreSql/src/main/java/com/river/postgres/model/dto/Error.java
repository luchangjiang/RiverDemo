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
@Setter
@ToString
@ApiModel(value="错误信息")
public class Error implements Serializable {

    private static final long serialVersionUID = 7660756960387438399L;

    @ApiModelProperty(value="错误代码", required = true)
    private int code; // Error code

    @ApiModelProperty(value="错误消息")
    private String message; // Error message

}
