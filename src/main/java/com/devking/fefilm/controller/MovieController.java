package com.devking.fefilm.controller;

import com.devking.fefilm.model.*;
import com.devking.fefilm.model.request.MovieRequest;
import com.devking.fefilm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.IntStream;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private BookmarkService bookmarkService;
    @Autowired
    private UserService userService;


    @GetMapping("/movies")
    public ModelAndView movies(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "16") int size, @RequestParam(value = "orderByColumn", defaultValue = "releaseYear") String orderByColumn, @RequestParam(value = "country", defaultValue = "Country") String country, @RequestParam(value = "genre", defaultValue = "Genre") String genre, Model model) {
        MovieRequest allMoviesRequest;
        Page<Movie> allMovies;
        if (!country.equalsIgnoreCase("Country") && genre.equalsIgnoreCase("Genre")) {
            allMoviesRequest = new MovieRequest("country", country, "desc", page - 1, size, orderByColumn);
        } else if (country.equalsIgnoreCase("Country") && !genre.equalsIgnoreCase("Genre")) {
            allMoviesRequest = new MovieRequest("genre", genre, "desc", page - 1, size, orderByColumn);
        } else if (!country.equalsIgnoreCase("Country") && !genre.equalsIgnoreCase("Genre")) {
            allMoviesRequest = new MovieRequest("genre&country", genre + "&" + country, "desc", page - 1, size, orderByColumn);
        } else {
            allMoviesRequest = new MovieRequest("", "", "desc", page - 1, size, orderByColumn);
        }
        allMovies = movieService.getAllMoviesWithPagination(allMoviesRequest);
        int[] pageNumberList = IntStream.range(1, allMovies.getTotalPages()+ 1).toArray();
        List<Genre> genreList = genreService.getAllGenre();
        List<Country> countryList = countryService.getAllCountry();
        User currentUser = userService.getCurrentLoggingUser();
        boolean isAdmin = userService.isAdmin();

        model.addAttribute("title", "Popular Movies");
        model.addAttribute("allMovies", allMovies);
        model.addAttribute("pageNumberList", pageNumberList);
        model.addAttribute("activePage", page);
        model.addAttribute("genreList", genreList);
        model.addAttribute("countryList", countryList);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("country", country);
        model.addAttribute("genre", genre);

        ModelAndView modelAndView = new ModelAndView("movies");
        return modelAndView;
    }

    @GetMapping("/movies/countries/{country}")
    public ModelAndView moviesByCountry(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "16") int size, Model model, @PathVariable String country) {
        MovieRequest movieByCountryRequest = new MovieRequest("country", country, "desc", page - 1, size, "releaseYear");
        Page<Movie> allMovies = movieService.getAllMoviesWithPagination(movieByCountryRequest);
        int[] pageNumberList = IntStream.range(1, allMovies.getTotalPages() + 1).toArray();
        List<Genre> genreList = genreService.getAllGenre();
        List<Country> countryList = countryService.getAllCountry();
        User currentUser = userService.getCurrentLoggingUser();
        boolean isAdmin = userService.isAdmin();

        model.addAttribute("title", country + " Movies");
        model.addAttribute("allMovies", allMovies);
        model.addAttribute("pageNumberList", pageNumberList);
        model.addAttribute("activePage", page);
        model.addAttribute("genreList", genreList);
        model.addAttribute("countryList", countryList);
        model.addAttribute("country", country);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("isAdmin", isAdmin);

        ModelAndView modelAndView = new ModelAndView("movies-countries");
        return modelAndView;
    }

    @GetMapping("/movies/genres/{genre}")
    public ModelAndView moviesByGenre(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "16") int size, Model model, @PathVariable String genre) {
        MovieRequest movieByGenreRequest = new MovieRequest("genre", genre, "desc", page - 1, size, "releaseYear");
        Page<Movie> allMovies = movieService.getAllMoviesWithPagination(movieByGenreRequest);
        int[] pageNumberList = IntStream.range(1, allMovies.getTotalPages() + 1).toArray();
        List<Genre> genreList = genreService.getAllGenre();
        List<Country> countryList = countryService.getAllCountry();
        User currentUser = userService.getCurrentLoggingUser();
        boolean isAdmin = userService.isAdmin();

        model.addAttribute("title", genre + " Movies");
        model.addAttribute("allMovies", allMovies);
        model.addAttribute("pageNumberList", pageNumberList);
        model.addAttribute("activePage", page);
        model.addAttribute("genreList", genreList);
        model.addAttribute("countryList", countryList);
        model.addAttribute("genre", genre);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("isAdmin", isAdmin);

        ModelAndView modelAndView = new ModelAndView("movies-genres");
        return modelAndView;
    }

    @GetMapping("/movies/detail/{id}")
    public ModelAndView getMovieDetail(@PathVariable String id, Model model) {
        Movie movie = movieService.getMovieById(Integer.parseInt(id)).orElseThrow(null);
        List<Genre> movieGenreList = movieService.getGenresByMovieTitle(movie.getTitle());
        List<Country> movieCountryList = movieService.getCountriesByMovieTitle(movie.getTitle());
        MovieRequest recommendedMoviesRequest = new MovieRequest("title", movie.getTitle(), "desc", 0, 4, "releaseYear");
        Page<Movie> recommendedMovies = movieService.getRecommendedMovies(movieGenreList.stream().map(Genre::getName).toList(), recommendedMoviesRequest);
        List<Genre> genreList = genreService.getAllGenre();
        List<Country> countryList = countryService.getAllCountry();
        User currentUser = userService.getCurrentLoggingUser();
        boolean isAdmin = userService.isAdmin();

        model.addAttribute("movie", movie);
        model.addAttribute("movieGenreList", movieGenreList);
        model.addAttribute("movieCountryList", movieCountryList);
        model.addAttribute("recommendedMovies", recommendedMovies);
        model.addAttribute("genreList", genreList);
        model.addAttribute("countryList", countryList);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("isAdmin", isAdmin);

        ModelAndView modelAndView = new ModelAndView("detail");
        return modelAndView;
    }

    @PostMapping("/movies/bookmarks")
    public String addBookmark(@ModelAttribute Bookmark bookmark, Model model) {
        Bookmark existedBookmark = bookmarkService.getBookmarkByMovie(bookmark.getMovie());
        if (existedBookmark == null) {
            bookmarkService.saveBookmark(bookmark);
        } else {
            bookmarkService.deleteBookmark(existedBookmark);
        }
        model.addAttribute("bookmark", bookmark);
        return "redirect:/movies/bookmarks?page=1&size=8";
    }

    @GetMapping("/movies/bookmarks")
    public String getBookmark(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "16") int size, Model model) {
        Pageable pageable = PageRequest.of(page - 1, size);
        User currentUser = userService.getCurrentLoggingUser();
        boolean isAdmin = userService.isAdmin();

        Page<Movie> allMovies = bookmarkService.getAllBookmarkMovies(currentUser.getId(), pageable);
        int[] pageNumberList = IntStream.range(1, allMovies.getTotalPages() + 1).toArray();
        List<Genre> genreList = genreService.getAllGenre();
        List<Country> countryList = countryService.getAllCountry();

        model.addAttribute("title", "Bookmark Movies");
        model.addAttribute("allMovies", allMovies);
        model.addAttribute("pageNumberList", pageNumberList);
        model.addAttribute("activePage", page);
        model.addAttribute("genreList", genreList);
        model.addAttribute("countryList", countryList);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("isAdmin", isAdmin);

        return "movies-bookmarks";
    }

    @GetMapping("/movies/search")
    public String getSearchMovie(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "16") int size, @RequestParam(value = "q", defaultValue = "") String query, Model model) {
        MovieRequest movieBySearchRequest = new MovieRequest("title", query, "desc", page - 1, size, "releaseYear");
        Page<Movie> allMovies = movieService.getAllMoviesWithPagination(movieBySearchRequest);
        int[] pageNumberList = IntStream.range(1, allMovies.getTotalPages() + 1).toArray();
        List<Genre> genreList = genreService.getAllGenre();
        List<Country> countryList = countryService.getAllCountry();
        User currentUser = userService.getCurrentLoggingUser();
        boolean isAdmin = userService.isAdmin();

        model.addAttribute("title", "Search Movies");
        model.addAttribute("allMovies", allMovies);
        model.addAttribute("pageNumberList", pageNumberList);
        model.addAttribute("activePage", page);
        model.addAttribute("genreList", genreList);
        model.addAttribute("countryList", countryList);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("query", query);
        model.addAttribute("isAdmin", isAdmin);

        return "movies-search";
    }
}
