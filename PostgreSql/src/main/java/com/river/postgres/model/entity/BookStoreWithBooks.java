package com.river.postgres.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;


@Accessors(chain = true)
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@ApiModel(value="书店及在售信息")
public class BookStoreWithBooks extends BookStore {

    private static final long serialVersionUID = -740463675258248874L;

    @ApiModelProperty(value="id", required = true)
    private long id;

    @ApiModelProperty(value="书店名称", required = true)
    private String name;

    @ApiModelProperty(value="书店地址")
    private String address;

    @ApiModelProperty(value="在售书籍")
    private List<Book> books;
}
