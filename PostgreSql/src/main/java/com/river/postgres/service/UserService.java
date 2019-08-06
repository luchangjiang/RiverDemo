package com.river.postgres.service;

import com.river.postgres.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;


public interface UserService extends UserDetailsService {

    Optional<User> getUserById(Long id);

    boolean saveUser(User user);

    boolean modifyUserOnPasswordById(User user);

    boolean deleteUserById(Long id);

    List<User> getUserByPage(Integer offset, Integer pageSize);

    Integer getTotalPage(Integer perPage);
}
