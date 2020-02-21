package com.river.postgres.controller;

import com.river.postgres.constant.ResourceNameConstant;
import com.river.postgres.exception.ResourceNotFoundException;
import com.river.postgres.model.entity.BookStore;
import com.river.postgres.model.entity.BookStoreWithBooks;
import com.river.postgres.service.BookStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Api(value="bookStore", tags="书店管理")
@RestController
@RequestMapping("/bookStore")
public class BookStoreController {
    @Autowired
    private BookStoreService bookStoreService;

    @ApiOperation(value="按id获取书店详情", notes = "通过id获取书店的详细信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "storeId", value = "书店ID", required = true)})
    @GetMapping("/{storeId}")
    public ResponseEntity<Optional<BookStore>> getBookStoreById(@PathVariable Long storeId){
        return ResponseEntity.ok(bookStoreService.getBookStoreById(storeId));
    }

    @ApiOperation(value="获取书店名称列表", notes = "获取所有书籍的书店名称列表")
    @GetMapping("/getAllBookStoreNames")
    public ResponseEntity<List<String>> getAllBookStoreNames(){
        return ResponseEntity.ok(bookStoreService.getAllBookStoreNames());
    }

    @ApiOperation(value="按id获取书店信息", notes = "通过id获取书店的详细信息及在售图书的信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "storeId", value = "图书ID", required = true)})
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
