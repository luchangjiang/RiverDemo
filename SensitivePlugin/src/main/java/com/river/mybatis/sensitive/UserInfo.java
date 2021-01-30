package com.river.mybatis.sensitive;

import lombok.Data;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-12-27 11:01
 **/
@Data
public class UserInfo {
    private static final long serialVersionUID = -8938650956516110149L;
    private Long userId;
    @Sensitive(strategy = SensitiveStrategy.USERNAME)
    private String name;
    private Integer age;
}
