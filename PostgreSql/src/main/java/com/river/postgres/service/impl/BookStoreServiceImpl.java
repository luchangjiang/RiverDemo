package com.river.postgres.service.impl;

import com.river.postgres.mapper.BookStoreMapper;
import com.river.postgres.model.entity.BookStore;
import com.river.postgres.model.entity.BookStoreWithBooks;
import com.river.postgres.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookStoreServiceImpl implements BookStoreService {

    private final BookStoreMapper bookStoreMapper;

    @Autowired
    public BookStoreServiceImpl(BookStoreMapper bookStoreMapper) {
        this.bookStoreMapper = bookStoreMapper;
    }

    @Override
    public Optional<BookStore> getBookStoreById(Long id) {
        return Optional.ofNullable(bookStoreMapper.selectBookStoreById(id));
    }

    @Override
    public List<String> getAllBookStoreNames() {
        return bookStoreMapper
                .selectAllBookStores()
                .stream()
                .map(BookStore::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookStoreWithBooks> getBookStoreWithBooksById(Long id) {
        return Optional.ofNullable(bookStoreMapper.selectBookStoreWithBooksById(id));
    }
}
