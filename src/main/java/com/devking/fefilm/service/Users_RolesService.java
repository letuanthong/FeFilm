package com.devking.fefilm.service;

import com.devking.fefilm.model.User;
import com.devking.fefilm.model.Users_Roles;

import java.util.Optional;

public interface Users_RolesService {
    public Users_Roles save(Users_Roles usersRoles);
    public void removeUsersRoleById(int id);


}
