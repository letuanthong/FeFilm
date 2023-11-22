package com.devking.fefilm.controller;

import com.devking.fefilm.model.Movie;
import com.devking.fefilm.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @PostMapping
    public Movie addNewMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
