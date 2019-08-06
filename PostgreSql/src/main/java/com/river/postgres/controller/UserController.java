package com.river.postgres.controller;

import com.river.postgres.constant.PageConstant;
import com.river.postgres.model.dto.PaginatedResult;
import com.river.postgres.service.UserService;
import com.river.postgres.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getUsers(@RequestParam(value = "page", required = false) String pageString, @RequestParam(value = "per_page", required = false) String perPageString){
        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(perPageString, PageConstant.PER_PAGE);

        return ResponseEntity.ok(new PaginatedResult()
                .setData(userService.getUserByPage(page, perPage))
                .setCurrentPage(page)
                .setTotalPage(userService.getTotalPage(perPage))
                );
    }
}
