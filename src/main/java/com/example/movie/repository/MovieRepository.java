package com.example.movie.repository;

import java.util.*;
import com.example.movie.model.Movie;

public interface MovieRepository{
    List<Movie> getMovies();
    Movie addMovie(Movie movie);
    Movie getMovieById(int movieId);
}