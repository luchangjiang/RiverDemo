package com.river.postgres.mapper;

import com.river.postgres.model.entity.BookStore;
import com.river.postgres.model.entity.BookStoreWithBooks;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookStoreMapper {

    BookStore selectBookStoreById(Long id);

    List<BookStore> selectAllBookStores();

    BookStoreWithBooks selectBookStoreWithBooksById(Long id);

}
