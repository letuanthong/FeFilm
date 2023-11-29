package com.devking.fefilm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String parentId;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @Column(nullable = false)
    private String content;
    @CreationTimestamp
    private Date createdAt;
//    @ManyToMany
//    @JoinTable(
//            name = "movie_comments",
//            joinColumns = @JoinColumn(name = "movieId"),
//            inverseJoinColumns = @JoinColumn(name = "commentId")
//    )
//    private Set<Movie> movies;
}
