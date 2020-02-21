package com.river.postgres.service;

import com.river.postgres.model.entity.BookStore;
import com.river.postgres.model.entity.BookStoreWithBooks;

import java.util.List;
import java.util.Optional;


public interface BookStoreService {

    Optional<BookStore> getBookStoreById(Long id);

    List<String> getAllBookStoreNames();

    Optional<BookStoreWithBooks> getBookStoreWithBooksById(Long id);

}
