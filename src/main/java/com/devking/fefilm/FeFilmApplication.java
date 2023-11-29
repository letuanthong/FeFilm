package com.devking.fefilm;

import com.devking.fefilm.model.Role;
import com.devking.fefilm.model.User;
import com.devking.fefilm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
		//(exclude = SecurityAutoConfiguration.class)
public class FeFilmApplication {
	public static void main(String[] args) {
		SpringApplication.run(FeFilmApplication.class, args);
	}

}
