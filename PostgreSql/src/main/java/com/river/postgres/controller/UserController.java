package com.river.postgres.controller;

import com.river.postgres.constant.PageConstant;
import com.river.postgres.model.dto.PaginatedResult;
import com.river.postgres.service.UserService;
import com.river.postgres.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value="user", tags="用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @ApiOperation(value="获取用户清单", notes = "分页获取用户清单")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "第几页", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true)
    })
    @GetMapping
    public ResponseEntity<?> getUsers(@RequestParam(value = "page", required = false) String pageString, @RequestParam(value = "pageSize", required = false) String pageSizeString){
        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int pageSize = PageUtil.parsePerPage(pageSizeString, PageConstant.PAGE_SIZE);

        return ResponseEntity.ok(new PaginatedResult()
                .setData(userService.getUserByPage(page, pageSize))
                .setCurrentPage(page)
                .setTotalPage(userService.getTotalPage(pageSize))
                );
    }
}
