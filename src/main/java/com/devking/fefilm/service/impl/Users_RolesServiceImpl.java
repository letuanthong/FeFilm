package com.devking.fefilm.service.impl;

import com.devking.fefilm.model.Users_Roles;
import com.devking.fefilm.repository.Users_RolesRepository;
import com.devking.fefilm.service.Users_RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Users_RolesServiceImpl  implements Users_RolesService {
    @Autowired
    Users_RolesRepository users_rolesRepository;
    @Override
    public Users_Roles save(Users_Roles usersRoles) {
        return users_rolesRepository.save(usersRoles);
    }
}
