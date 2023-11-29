package com.devking.fefilm.service;

import com.devking.fefilm.model.Country;
import com.devking.fefilm.model.Genre;
import com.devking.fefilm.model.Movie;
import com.devking.fefilm.model.request.MovieRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    //List<Movie> getAllMovieBy(int id);

    Optional<Movie> getMovieById(int id);

    void removeMovieById(int id);

    void updateMovie(Movie movie);

    List<Movie> getAllMovie();
    Page<Movie> getAllMoviesWithPagination(MovieRequest movieRequest);
    Page<Movie> getRecommendedMovies(List<String> genreList, MovieRequest movieRequest);
    List<Genre> getGenresByMovieTitle(String title);
    List<Country> getCountriesByMovieTitle(String title);
}
