package com.devking.fefilm.service;

import com.devking.fefilm.model.Country;
import com.devking.fefilm.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User save(User user);
    User updateUser(User user);
    Optional<User> getUserByEmail(String email);
    User getCurrentLoggingUser();
    boolean isAdmin();

    public List<User> getAllUser();


    boolean emailExists(String email);
}
