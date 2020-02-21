package com.river.postgres.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@ApiModel(value="书籍及相关书店信息")
public class BookWithBookStore extends Book {

    private static final long serialVersionUID = -4858710159989616286L;

    @ApiModelProperty(value="书店信息", required = true)
    private BookStore bookStore;

}
