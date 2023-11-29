package com.devking.fefilm.repository;

import com.devking.fefilm.model.Bookmark;
import com.devking.fefilm.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    @Query("select movie from Bookmark")
    Page<Movie> getAllBookmarkMovies(Pageable pageable);
    Bookmark findByMovie(Movie movie);
}
