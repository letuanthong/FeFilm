package com.devking.fefilm.service.impl;

import com.devking.fefilm.model.Movie;
import com.devking.fefilm.model.request.MovieRequest;
import com.devking.fefilm.repository.MovieRepository;
import com.devking.fefilm.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Override
    public Optional<Movie> getMovieById(int id) {
        return movieRepository.findById(id);
    }

    @Override
    public void removeMovieById(int id) {
        movieRepository.deleteById(id);
    }

    @Override
    public void updateMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }

    @Override
    public Page<Movie> getAllMoviesWithPagination(MovieRequest movieRequest) {
        Page<Movie> movies;
        Pageable pageRequest;
        pageRequest = PageRequest.of(movieRequest.getPage(), movieRequest.getItemsPerPage(), Sort.by(movieRequest.getDirection().equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, movieRequest.getOrderByColumn()));
        switch (movieRequest.getField()) {
            case "country":
                movies = movieRepository.findAllDistinctByCountry(movieRequest.getValue(), pageRequest);
                break;
            default:
                movies = movieRepository.findAllDistinct(pageRequest);
                break;
        }
        return movies;
//        return movieRepository.findAllDistinct(pageRequest);
    }
}
