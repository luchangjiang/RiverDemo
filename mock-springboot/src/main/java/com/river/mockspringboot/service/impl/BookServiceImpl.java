package com.river.mockspringboot.service.impl;

import com.river.mockspringboot.entity.Book;
import com.river.mockspringboot.service.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author Dax
 * @since 14:55  2019-07-23
 */
@Service
public class BookServiceImpl implements BookService {


    @Override
    public Book queryByTitle(String title) {
        Book book = new Book();
        book.setAuthor("tom");
        book.setPrice(77.56);
        book.setReleaseTime(LocalDate.of(2018, 7, 4));
        book.setTitle(title);
        return book;
    }
}
