package com.devking.fefilm.controller;

import com.devking.fefilm.model.Genre;
import com.devking.fefilm.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;

    @PostMapping
    public Genre addNewGenre(@RequestBody Genre genre) {
        return genreRepository.save(genre);
    }

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}
