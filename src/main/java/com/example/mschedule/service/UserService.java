package com.example.mschedule.service;

import com.example.mschedule.entity.User;

import java.util.List;

public interface UserService {

    User getUserById(Integer id);

    List<User> getAllUser();

    Boolean getFreeStatus(User user);

}
