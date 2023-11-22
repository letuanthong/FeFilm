package com.devking.fefilm.controller;

import com.devking.fefilm.model.Country;
import com.devking.fefilm.model.Episode;
import com.devking.fefilm.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/episodes")
public class EpisodeController {
    @Autowired
    private EpisodeRepository episodeRepository;

    @PostMapping
    public Episode addNewEpisode(@RequestBody Episode episode) {
        return episodeRepository.save(episode);
    }

    @GetMapping
    public List<Episode> getAllEpisodes() {
        return episodeRepository.findAll();
    }
}
