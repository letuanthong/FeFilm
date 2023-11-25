package com.devking.fefilm.service;

import com.devking.fefilm.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    public List<Genre> getAllGenre();

    public void updateGenre(Genre genre);

    public void removeGenreById(int id);

    public Optional<Genre> getGenreById(int id);
}
