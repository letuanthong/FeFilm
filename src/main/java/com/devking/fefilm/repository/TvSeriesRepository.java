package com.devking.fefilm.repository;

import com.devking.fefilm.model.TvSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TvSeriesRepository extends JpaRepository<TvSeries, Integer> {
}
