package com.devking.fefilm.repository;

import com.devking.fefilm.model.User;
import com.devking.fefilm.model.Users_Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Users_RolesRepository extends JpaRepository<Users_Roles,Integer> {
    Optional<Users_Roles> findRoleByUserId(int id);
}
