package com.devking.fefilm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private String avatar;
    private String display_name;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(name = "userId"),
//            inverseJoinColumns = @JoinColumn(name = "roleId")
//    )
    private Set<Users_Roles> usersRoles;

    public User() {
        this.usersRoles = new HashSet<>();
    }

    public User(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.usersRoles = user.getUsersRoles();
    }
}
