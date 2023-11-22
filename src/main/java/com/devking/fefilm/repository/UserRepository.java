package com.devking.fefilm.repository;

import com.devking.fefilm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
