package com.devking.fefilm.controller;

import com.devking.fefilm.model.Bookmark;
import com.devking.fefilm.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookmarks")
public class BookmarkController {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @PostMapping
    public Bookmark addNewBookmark(@RequestBody Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    @GetMapping
    public List<Bookmark> getAllBookmarks() {
        return bookmarkRepository.findAll();
    }
}
