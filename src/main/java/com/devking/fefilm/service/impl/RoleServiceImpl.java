package com.devking.fefilm.service.impl;

import com.devking.fefilm.model.Role;
import com.devking.fefilm.repository.RoleRepository;
import com.devking.fefilm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> findRoleById(int id) {
        return roleRepository.findById(id);
    }
}
