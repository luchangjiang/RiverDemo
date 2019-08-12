package com.river.mockspringboot.api;

import com.river.mockspringboot.entity.Book;
import com.river.mockspringboot.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Dax
 * @since 10:24  2019-07-23
 */
@RestController
@RequestMapping("/book")
public class BookApi {
    @Resource
    private BookService bookService;

    @GetMapping("/get")
    public Book getBook(String title) {
        return bookService.queryByTitle(title);
    }

}
