package com.devking.fefilm.service;

import com.devking.fefilm.model.User;

import java.util.Optional;

public interface UserService {
    public User save(User user);
    User updateUser(User user);
    Optional<User> getUserByEmail(String email);
    User getCurrentLoggingUser();
    boolean isAdmin();

    boolean emailExists(String email);
}
