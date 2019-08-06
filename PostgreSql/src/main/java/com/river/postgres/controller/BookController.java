package com.river.postgres.controller;

import com.river.postgres.constant.PageConstant;
import com.river.postgres.constant.ResourceNameConstant;
import com.river.postgres.exception.ResourceNotFoundException;
import com.river.postgres.model.dto.PaginatedResult;
import com.river.postgres.model.entity.Book;
import com.river.postgres.model.entity.BookWithBookStore;
import com.river.postgres.service.BookService;
import com.river.postgres.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Api(value="book", tags="图书管理")
@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ApiOperation(value="获取图书清单", notes = "分页获取图书清单")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "第几页", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true)
    })
    @GetMapping
    public ResponseEntity<?> getBooks(@RequestParam(value = "page", required = false) String pageString,
                                      @RequestParam(value = "pageSize", required = false) String pageSizeString) {
        // Parse request parameters
        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int pageSize = PageUtil.parsePerPage(pageSizeString, PageConstant.PAGE_SIZE);

        return ResponseEntity
                .ok(new PaginatedResult()
                        .setData(bookService.getBooksByPage(page, pageSize))
                        .setCurrentPage(page)
                        .setTotalPage(bookService.getTotalPage(pageSize)));
    }

    @ApiOperation(value="按id获取图书详情", notes = "通过id获取图书的详细信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "bookId", value = "图书ID", required = true)})
    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable Long bookId) {
        return bookService
                .getBookById(bookId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException()
                        .setResourceName(ResourceNameConstant.BOOK)
                        .setId(bookId));
    }

    @ApiOperation(value="按作者获取图书清单", notes = "通过作者名称获取图书的详细信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "author", value = "作者", required = true)})
    @GetMapping("/getBooksByAuthor")
    public ResponseEntity<List<Book>> getBooksByAuthor(String author){
        return ResponseEntity.ok(bookService.getBooksByAuthor(author));
    }

    @ApiOperation(value="获取书名列表", notes = "获取所有书籍的书名清单")
    @GetMapping("/getAllBookNames")
    public ResponseEntity<List<String>> getAllBookNames(){
        return ResponseEntity.ok(bookService.getAllBookNames());
    }

    @ApiOperation(value="按id获取图书信息", notes = "通过id获取图书的详细信息及相关书店的信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "bookId", value = "图书ID", required = true)})
    @GetMapping("/getBookWithBookStoreById/{bookId}")
    public ResponseEntity<Optional<BookWithBookStore>> getBookWithBookStoreById(@PathVariable Long bookId){
        assertBookExist(bookId);

        return ResponseEntity.ok(bookService.getBookWithBookStoreById(bookId));
    }

    @ApiOperation(value="添加图书", notes = "添加图书信息")
    @PostMapping
    public ResponseEntity<?> postBook(@RequestBody @Valid Book book) {
        bookService.saveBook(book);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(book.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(book);

    }

    @ApiOperation(value="修改图书", notes = "修改图书信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "bookId", value = "图书ID", required = true)})
    @PutMapping("/{bookId}")
    public ResponseEntity<?> putBook(@PathVariable Long bookId, @RequestBody @Valid Book book) {
        assertBookExist(bookId);

        bookService.modifyBookOnNameById(book.setId(bookId));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(book);
    }

    @ApiOperation(value="删除图书", notes = "删除图书信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "bookId", value = "图书ID", required = true)})
    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId) {
        assertBookExist(bookId);

        bookService.deleteBookById(bookId);

        return ResponseEntity
                .noContent()
                .build();
    }

    /********************************** HELPER METHOD **********************************/
    private void assertBookExist(Long bookId) {
        bookService
                .getBookById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException()
                        .setResourceName(ResourceNameConstant.BOOK)
                        .setId(bookId));
    }

}
