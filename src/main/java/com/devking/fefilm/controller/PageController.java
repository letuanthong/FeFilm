package com.devking.fefilm.controller;

import com.devking.fefilm.model.Country;
import com.devking.fefilm.model.Genre;
import com.devking.fefilm.model.Movie;
import com.devking.fefilm.model.request.MovieRequest;
import com.devking.fefilm.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.IntStream;

@Controller
public class PageController {
    @Autowired
    private MovieService movieService;
    @GetMapping("")
    public ModelAndView index(Model model) {
        MovieRequest carouselMovieRequest = new MovieRequest("", "", "desc", 0, 3, "releaseYear");
        MovieRequest popularMovieRequest = new MovieRequest("", "", "desc", 0, 5, "imdb");
        MovieRequest chineseMovieRequest = new MovieRequest("country", "China", "desc", 0, 5, "imdb");
        MovieRequest koreaMovieRequest = new MovieRequest("country", "South Korea", "desc", 0, 5, "imdb");
        MovieRequest japanMovieRequest = new MovieRequest("country", "Japan", "desc", 0, 5, "imdb");
        MovieRequest hongkongMovieRequest = new MovieRequest("country", "HongKong", "desc", 0, 5, "imdb");

        Page<Movie> carouselMovies = movieService.getAllMoviesWithPagination(carouselMovieRequest);
        Page<Movie> popularMovies = movieService.getAllMoviesWithPagination(popularMovieRequest);
        Page<Movie> chineseMovies = movieService.getAllMoviesWithPagination(chineseMovieRequest);
        Page<Movie> koreaMovies = movieService.getAllMoviesWithPagination(koreaMovieRequest);
        Page<Movie> japanMovies = movieService.getAllMoviesWithPagination(japanMovieRequest);
        Page<Movie> hongKongMovies = movieService.getAllMoviesWithPagination(hongkongMovieRequest);

        model.addAttribute("carouselMovies", carouselMovies);
        model.addAttribute("popularMovies", popularMovies);
        model.addAttribute("chineseMovies", chineseMovies);
        model.addAttribute("koreaMovies", koreaMovies);
        model.addAttribute("japanMovies", japanMovies);
        model.addAttribute("hongKongMovies", hongKongMovies);
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping("/movies")
    public ModelAndView movies(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "16") int size, @RequestParam(value = "orderByColumn", defaultValue = "releaseYear") String orderByColumn, Model model) {
        MovieRequest allMoviesRequest = new MovieRequest("", "", "desc", page - 1, size, orderByColumn);
        Page<Movie> allMovies = movieService.getAllMoviesWithPagination(allMoviesRequest);
        int[] pageNumberList = IntStream.range(1, allMovies.getTotalPages()).toArray();

        model.addAttribute("title", "Popular Movies");
        model.addAttribute("allMovies", allMovies);
        model.addAttribute("pageNumberList", pageNumberList);
        model.addAttribute("activePage", page);

        ModelAndView modelAndView = new ModelAndView("movies");
        return modelAndView;
    }

    @GetMapping("/movies/countries/{country}")
    public ModelAndView moviesByCountry(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "16") int size, Model model, @PathVariable String country) {
        MovieRequest movieByCountryRequest = new MovieRequest("country", country, "desc", page - 1, size, "releaseYear");
        Page<Movie> allMovies = movieService.getAllMoviesWithPagination(movieByCountryRequest);
        int[] pageNumberList = IntStream.range(1, allMovies.getTotalPages()).toArray();

        model.addAttribute("title", country + " Movies");
        model.addAttribute("allMovies", allMovies);
        model.addAttribute("pageNumberList", pageNumberList);
        model.addAttribute("activePage", page);

        ModelAndView modelAndView = new ModelAndView("movies");
        return modelAndView;
    }

    @GetMapping("/movies/detail/{id}")
    public ModelAndView getMovieDetail(@PathVariable String id, Model model) {
        Movie movie = movieService.getMovieById(Integer.parseInt(id)).orElseThrow(null);
        List<Genre> genreList = movieService.getGenresByMovieTitle(movie.getTitle());
        List<Country> countryList = movieService.getCountriesByMovieTitle(movie.getTitle());
        MovieRequest recommendedMoviesRequest = new MovieRequest("title", movie.getTitle(), "desc", 0, 4, "releaseYear");
        Page<Movie> recommendedMovies = movieService.getRecommendedMovies(genreList.stream().map(Genre::getName).toList(), recommendedMoviesRequest);

        model.addAttribute("movie", movie);
        model.addAttribute("genreList", genreList);
        model.addAttribute("countryList", countryList);
        model.addAttribute("recommendedMovies", recommendedMovies);
        ModelAndView modelAndView = new ModelAndView("detail");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }
    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }
    @GetMapping("/forgot")
    public ModelAndView forgot() {
        ModelAndView modelAndView = new ModelAndView("forgot");
        return modelAndView;
    }

}
