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
    @Column(nullable = false)
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
    @ManyToMany
    @JoinTable(
            name = "movie_countries",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "countryId")
    )
    private Set<Country> countries;
    @ManyToMany
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "genreId")
    )
    private Set<Genre> genres;
    @ManyToMany(mappedBy = "movies")
    private Set<Comment> comments;
}
