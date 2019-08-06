package com.river.postgres.mapper;

import com.river.postgres.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectUserById(Long id);

    User selectUserByUsername(String username);

    List<User> selectAllUsers();

    Integer insertUser(User user);

    Integer updateUserOnPasswordById(User user);

    Integer deleteUserById(Long id);

    List<User> selectUserByPage(@Param("offset") Integer offset, @Param("pageSize") Integer perPage);

    Integer selectCount();
}
