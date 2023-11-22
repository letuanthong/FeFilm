package com.devking.fefilm.controller;

import com.devking.fefilm.model.TvSeries;
import com.devking.fefilm.repository.TvSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tvseries")
public class TvSeriesController {
    @Autowired
    private TvSeriesRepository tvSeriesRepository;

    @PostMapping
    public TvSeries addNewTvSeries(@RequestBody TvSeries tvSeries) {
        return tvSeriesRepository.save(tvSeries);
    }

    @GetMapping
    public List<TvSeries> getAllTvSeries() {
        return tvSeriesRepository.findAll();
    }
}
