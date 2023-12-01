package com.devking.fefilm.service.impl;

import com.devking.fefilm.model.Bookmark;
import com.devking.fefilm.model.Movie;
import com.devking.fefilm.repository.BookmarkRepository;
import com.devking.fefilm.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkServiceIml implements BookmarkService {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Override
    public List<Bookmark> getAllBookmark() {
        return bookmarkRepository.findAll();
    }

    @Override
    public Bookmark saveBookmark(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    @Override
    public Page<Movie> getAllBookmarkMovies(Integer userID, Pageable pageable) {
        return bookmarkRepository.getAllBookmarkMoviesByUserID(userID, pageable);
    }

    @Override
    public Bookmark getBookmarkByMovie(Movie movie) {
        return bookmarkRepository.findByMovie(movie);
    }
    @Override
    public void deleteBookmark(Bookmark bookmark) {
        bookmarkRepository.delete(bookmark);
    }
}
