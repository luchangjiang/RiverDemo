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
@ApiModel(value="用户信息")
public class User implements Serializable {

    private static final long serialVersionUID = 7698862379923111158L;

    @ApiModelProperty(value="id", required = true)
    private Long id;

    @ApiModelProperty(value="用户名称", required = true)
    private String username;

    @ApiModelProperty(value="用户密码", required = true)
    private String password;
}
