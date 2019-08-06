package com.river.postgres.service.impl;

import com.river.postgres.mapper.BookMapper;
import com.river.postgres.model.entity.Book;
import com.river.postgres.model.entity.BookWithBookStore;
import com.river.postgres.service.BookService;
import com.river.postgres.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return Optional.ofNullable(bookMapper.selectBookById(id));
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return bookMapper.selectBooksByAuthor(author);
    }

    @Override
    public List<Book> getBooksByPage(Integer page, Integer pageSize) {
        Integer offset = PageUtil.calculateOffset(page, pageSize);
        return bookMapper.selectBooksByPage(offset, pageSize);
    }

    @Override
    public List<String> getAllBookNames() {
        return bookMapper
                .selectAllBooks()
                .stream()
                .map(Book::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookWithBookStore> getBookWithBookStoreById(Long id) {
        return Optional.ofNullable(bookMapper.selectBookWithBookStoreById(id));
    }

    @Override
    public Integer getTotalPage(Integer pageSize) {
        return PageUtil.calculateTotalPage(bookMapper.selectCount(), pageSize);
    }

    @Override
    @Transactional
    public boolean saveBook(Book book) {
        return bookMapper.insertBook(book) > 0;
    }

    @Override
    @Transactional
    public boolean modifyBookOnNameById(Book book) {
        return bookMapper.updateBookOnNameById(book) > 0;
    }

    @Override
    @Transactional
    public boolean deleteBookById(Long id) {
        return bookMapper.deleteBookById(id) > 0;
    }
}
