package com.devking.fefilm.service;

import com.devking.fefilm.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findRoleById(int id);
}
