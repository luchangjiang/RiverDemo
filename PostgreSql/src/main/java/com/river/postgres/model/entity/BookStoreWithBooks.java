package com.river.postgres.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;


@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class BookStoreWithBooks extends BookStore {

    private static final long serialVersionUID = -740463675258248874L;

    private long id;
    private String name;
    private String address;
    private List<Book> books;

}
