package com.example.Bridge_Pattern.service;

import com.example.Bridge_Pattern.dto.User;
import com.example.Bridge_Pattern.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getUser(String id) {
        return userMapper.getUser(id);
    }

}
