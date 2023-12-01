package com.devking.fefilm.service;

import com.devking.fefilm.model.Bookmark;
import com.devking.fefilm.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookmarkService {
    List<Bookmark> getAllBookmark();
    Bookmark saveBookmark(Bookmark bookmark);
    Page<Movie> getAllBookmarkMovies(Integer userID, Pageable pageable);
    Bookmark getBookmarkByMovie(Movie movie);
    void deleteBookmark(Bookmark bookmark);
}
