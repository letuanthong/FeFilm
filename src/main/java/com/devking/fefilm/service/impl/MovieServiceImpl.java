package com.devking.fefilm.service.impl;

import com.devking.fefilm.model.Movie;
import com.devking.fefilm.repository.MovieRepository;
import com.devking.fefilm.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
