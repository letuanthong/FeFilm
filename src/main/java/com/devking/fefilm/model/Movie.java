package com.devking.fefilm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String releaseYear;
    @Lob
    @Column(nullable = false, length = 512)
    private String overview;
    @Column(nullable = false)
    private String imageVerticalPath;
    @Column(nullable = false)
    private String imageHorizontalPath;
    @Column(nullable = false)
    private String videoPath;
    private float imdb;
    private String director;
    private String cast;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", referencedColumnName = "genre_id")
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    private Country country;

    @ManyToMany(mappedBy = "movies")
    private Set<Comment> comments;
}
