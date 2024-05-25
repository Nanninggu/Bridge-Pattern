package com.example.Bridge_Pattern.service;

import com.example.Bridge_Pattern.dto.User;

import java.util.List;

public interface UserService {
    List<User> getUser(String id);

}
