/*
 *    Copyright (c) 2018-2025, Eddid All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: River (River.lu@newtype.io)
 */
package com.river.postgres.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.river.postgres.entity.Book;
import com.river.postgres.service.BookService;
import com.river.postgres.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * dfd
 *
 * @author River
 * @date 2019-08-07 10:30:37
 */
@Api(value="book", tags="图书管理")
@RestController
@AllArgsConstructor
@RequestMapping("/book" )
public class BookController {

    private final BookService bookService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param book dfd
     * @return
     */
    @ApiOperation(value="获取图书清单", notes = "分页获取图书清单")
    @GetMapping("/page" )
    public R getBookPage(Page page, Book book) {
        return R.ok(bookService.page(page, Wrappers.query(book)));
    }


    /**
     * 通过id查询dfd
     * @param id id
     * @return R
     */
    @ApiOperation(value="按id获取图书详情", notes = "通过id获取图书的详细信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "图书ID", required = true)})
    @GetMapping("/{id}" )
    public R getById(@Valid @PathVariable("id" ) Integer id) {
        return R.ok(bookService.getById(id));
    }

    /**
     * 新增dfd
     * @param book dfd
     * @return R
     */
    //@SysLog("新增dfd" )
    //@PreAuthorize("@pms.hasPermission('murugar_book_add')" )
    @ApiOperation(value="添加图书", notes = "添加图书信息")
    @PostMapping
    public R save(@Valid @RequestBody Book book) {
        return R.ok(bookService.save(book));
    }

    /**
     * 修改dfd
     * @param book dfd
     * @return R
     */
    //@SysLog("修改dfd" )
    //@PreAuthorize("@pms.hasPermission('murugar_book_edit')" )
    @ApiOperation(value="修改图书", notes = "修改图书信息")
    @PutMapping
    public R updateById(@Valid @RequestBody Book book) {
        return R.ok(bookService.updateById(book));
    }

    /**
     * 通过id删除dfd
     * @param id id
     * @return R
     */
    //@SysLog("删除dfd" )
    //@PreAuthorize("@pms.hasPermission('murugar_book_del')" )
    @ApiOperation(value="删除图书", notes = "删除图书信息")
    @DeleteMapping("/{id}" )
    public R removeById(@Valid @PathVariable Integer id) {
        return R.ok(bookService.removeById(id));
    }

}
