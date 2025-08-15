package com.example.movie.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

import com.example.movie.service.MovieJpaService;
import com.example.movie.model.Movie;

@RestController
public class MovieController{
    @Autowired
    public MovieJpaService service;

    @GetMapping("/movies")
    public List<Movie> getMovies(){
        return service.getMovies();
    }

    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie movie){
        return service.addMovie(movie);
    }

    @GetMapping("/movies/{movieId}")
    public Movie getMovieById(@PathVariable("movieId") int movieId){
        return service.getMovieById(movieId);
    }
}