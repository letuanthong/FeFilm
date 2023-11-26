package com.devking.fefilm.repository;

import com.devking.fefilm.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query(value = "select * from movies group by title", nativeQuery = true)
    Page<Movie> findAllDistinct(Pageable pageable);
    @Query(value = "select m.id, imdb, title, releaseYear, imageVerticalPath, cast, director, overview, imageHorizontalPath, videoPath, m.country_id, genre_id from movies m join countries c on m.country_id = c.country_id where c.name like ?1 group by title", nativeQuery = true)
    Page<Movie> findAllDistinctByCountry(String country, Pageable pageable);
    @Query(value = "select m.id, imdb, title, releaseYear, imageVerticalPath, cast, director, overview, imageHorizontalPath, videoPath, m.country_id, genre_id from movies m join genres g on m.genre_id = g.genre_id where g.name like ?1 group by title", nativeQuery = true)
    Page<Movie> findAllDistinctByGenre(String genre, Pageable pageable);
}
