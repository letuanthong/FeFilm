package com.devking.fefilm.controller;

import com.devking.fefilm.model.Comment;
import com.devking.fefilm.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @PostMapping
    public Comment addNewComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
