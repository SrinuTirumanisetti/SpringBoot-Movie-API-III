package com.example.movie.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.*;
import com.example.movie.model.Movie;
import com.example.movie.repository.MovieJpaRepository;

@Service
public class MovieJpaService {
    @Autowired
    private MovieJpaRepository movieJpaRepository;

    public List<Movie> getMovies() {
        return movieJpaRepository.findAll();
    }

    public Movie addMovie(Movie movie) {
        return movieJpaRepository.save(movie);
    }

    public Movie getMovieById(int movieId) {
        return movieJpaRepository.findById(movieId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
    }

    public Movie updateMovie(int movieId, Movie updatedMovie) {
        Movie existingMovie = movieJpaRepository.findById(movieId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
        existingMovie.setMovieName(updatedMovie.getMovieName());
        existingMovie.setLeadActor(updatedMovie.getLeadActor());
        return movieJpaRepository.save(existingMovie);
    }

    public void deleteMovie(int movieId) {
        Movie movie = movieJpaRepository.findById(movieId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
        movieJpaRepository.delete(movie);
    }
}
