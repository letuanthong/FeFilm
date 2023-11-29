package com.devking.fefilm.repository;

import com.devking.fefilm.model.Country;
import com.devking.fefilm.model.Genre;
import com.devking.fefilm.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query(value = "select * from movies group by title", nativeQuery = true)
    Page<Movie> findAllDistinct(Pageable pageable);
    @Query(value = "select m.id, imdb, title, releaseYear, imageVerticalPath, cast, director, overview, imageHorizontalPath, videoPath, m.country_id, genre_id from movies m join countries c on m.country_id = c.country_id where c.name like ?1 group by title", nativeQuery = true)
    Page<Movie> findAllDistinctByCountry(String country, Pageable pageable);
    @Query(value = "select m.id, imdb, title, releaseYear, imageVerticalPath, cast, director, overview, imageHorizontalPath, videoPath, m.country_id, m.genre_id from movies m join genres g on m.genre_id = g.genre_id where g.name like ?1 group by title", nativeQuery = true)
    Page<Movie> findAllDistinctByGenre(String genre, Pageable pageable);
    @Query(value = "select new Genre(g.id, g.name) from Genre g join Movie m on m.genre.id = g.id where m.title like %?1%")
    List<Genre> findAllGenresByMovieTitle(String title);
    @Query(value = "select new Country (c.id, c.name) from Country c join Movie m on m.country.id = c.id where m.title like %?1% group by c.name")
    List<Country> findAllCountriesByMovieTitle(String title);
    @Query(value = "select m.id, m.title, m.releaseYear, m.overview, m.imageVerticalPath, m.imageHorizontalPath, m.videoPath, m.imdb, m.director, m.cast, m.genre_id, m.country_id from movies m join genres g on g.genre_id = m.genre_id where g.name in :genreList and m.title not like %:title% group by title", nativeQuery = true)
    Page<Movie> findRecommendedMovies(@Param("title") String title, @Param("genreList") List<String> genreList, Pageable pageable);
    @Query(value = "select * from movies where title like %?1% group by title", nativeQuery = true)
    Page<Movie> findDistinctByTitleContaining(String title, Pageable pageable);
}
