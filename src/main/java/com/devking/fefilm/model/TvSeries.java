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
@Table(name = "tv_series")
public class TvSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String release_year;
    @Column(nullable = false)
    private String overview;
    @Column(nullable = false)
    private String imageVerticalPath;
    @Column(nullable = false)
    private String imageHorizontalPath;
    private float imdb;
    private String director;
    private String cast;
    private int numEpisodes;
    @ManyToMany
    @JoinTable(
            name = "series_countries",
            joinColumns = @JoinColumn(name = "seriesId"),
            inverseJoinColumns = @JoinColumn(name = "countryId")
    )
    private Set<Country> countries;
    @ManyToMany
    @JoinTable(
            name = "series_genres",
            joinColumns = @JoinColumn(name = "seriesId"),
            inverseJoinColumns = @JoinColumn(name = "genreId")
    )
    private Set<Genre> genres;
    @ManyToMany(mappedBy = "tvSeries")
    private Set<Comment> comments;
}
