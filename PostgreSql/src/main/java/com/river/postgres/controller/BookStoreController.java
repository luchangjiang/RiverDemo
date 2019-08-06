package com.river.postgres.controller;

import com.river.postgres.constant.ResourceNameConstant;
import com.river.postgres.exception.ResourceNotFoundException;
import com.river.postgres.model.entity.BookStore;
import com.river.postgres.model.entity.BookStoreWithBooks;
import com.river.postgres.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookStore")
public class BookStoreController {
    @Autowired
    private BookStoreService bookStoreService;

    @GetMapping("/{storeId}")
    public ResponseEntity<Optional<BookStore>> getBookStoreById(@PathVariable Long storeId){
        return ResponseEntity.ok(bookStoreService.getBookStoreById(storeId));
    }

    @GetMapping("/getAllBookStoreNames")
    public ResponseEntity<List<String>> getAllBookStoreNames(){
        return ResponseEntity.ok(bookStoreService.getAllBookStoreNames());
    }

    @GetMapping("/getBookStoreWithBooksById/{storeId}")
    public ResponseEntity<Optional<BookStoreWithBooks>> getBookStoreWithBooksById(@PathVariable Long storeId){
        assertStoreExist(storeId);

        return ResponseEntity.ok(bookStoreService.getBookStoreWithBooksById(storeId));
    }

    /********************************** HELPER METHOD **********************************/
    private void assertStoreExist(Long storeId) {
        bookStoreService
                .getBookStoreById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException()
                        .setResourceName(ResourceNameConstant.STORE)
                        .setId(storeId));
    }
}
