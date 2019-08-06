package com.river.postgres.service;

import com.river.postgres.model.entity.Book;
import com.river.postgres.model.entity.BookWithBookStore;

import java.util.List;
import java.util.Optional;


public interface BookService {

    Optional<Book> getBookById(Long id);

    List<Book> getBooksByAuthor(String author);

    List<Book> getBooksByPage(Integer offset, Integer pageSize);

    List<String> getAllBookNames();

    Optional<BookWithBookStore> getBookWithBookStoreById(Long id);

    Integer getTotalPage(Integer perPage);

    boolean saveBook(Book book);

    boolean modifyBookOnNameById(Book book);

    boolean deleteBookById(Long id);

}
