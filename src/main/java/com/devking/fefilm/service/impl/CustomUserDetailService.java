package com.devking.fefilm.service.impl;

import com.devking.fefilm.model.CustomUserDetail;
import com.devking.fefilm.model.Role;
import com.devking.fefilm.model.User;
import com.devking.fefilm.model.Users_Roles;
import com.devking.fefilm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userService.getUserByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Users_Roles> roles = user.get().getUsersRoles();
        for (Users_Roles role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().getName()));
        }
        return new CustomUserDetail(user.get(),grantedAuthorities);
    }
}
