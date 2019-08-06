package com.river.postgres.service.impl;

import com.river.postgres.mapper.UserMapper;
import com.river.postgres.model.dto.MyUserDetails;
import com.river.postgres.model.entity.User;
import com.river.postgres.service.UserService;
import com.river.postgres.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find the user '" + username + "'");
        }

        // Not involve authorities, so pass null to authorities
        return new MyUserDetails(user, true, true, true, true, null);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userMapper.selectUserById(id));
    }

    @Override
    public boolean saveUser(User user) {
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public boolean modifyUserOnPasswordById(User user) {
        return userMapper.updateUserOnPasswordById(user) > 0;
    }

    @Override
    public boolean deleteUserById(Long id) {
        return userMapper.deleteUserById(id) > 0;
    }

    @Override
    public List<User> getUserByPage(Integer page, Integer pageSize){
        Integer offset = PageUtil.calculateOffset(page, pageSize);
        return userMapper.selectUserByPage(offset, pageSize);
    }

    @Override
    public Integer getTotalPage(Integer perPage){
        return PageUtil.calculateTotalPage(userMapper.selectCount(), perPage);
    }
}
