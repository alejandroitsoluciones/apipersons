package com.demo.service;


import java.util.Optional;

import com.demo.model.User;

public interface IUserService {
    User saveUser(User user);

    Optional<User> findByUsername(String username);

    void makeAdmin(String username);
}
