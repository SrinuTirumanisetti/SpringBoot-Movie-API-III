package com.example.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.movie.model.Movie;
import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.MovieJpaRepository;
import java.util.*;

@Service
public class MovieJpaService implements MovieRepository {

    private final MovieJpaRepository movieJpaRepository;

    @Autowired
    public MovieJpaService(MovieJpaRepository movieJpaRepository) {
        this.movieJpaRepository = movieJpaRepository;
    }

    @Override
    public List<Movie> getMovies() {
        return new ArrayList<>(movieJpaRepository.findAll());
    }

    @Override
    public Movie addMovie(Movie movie){
        return movieJpaRepository.save(movie);
    }

    @Override
    public Movie getMovieById(int movieId){
        return movieJpaRepository.findById(movieId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
    }

    @Override
    public Movie updateMovie(int movieId,Movie movie){
        Movie current = getMovieById(movieId);
        if(movie.getMovieName()!=null){
            current.setMovieName(movie.getMovieName());
        }
        if(movie.getLeadActor()!=null){
            current.setLeadActor(movie.getLeadActor());
        }
        return movieJpaRepository.save(current);
    }

    @Override
    public void deleteMovie(int movieId){
        if(!movieJpaRepository.existsById(movieId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        movieJpaRepository.deleteById(movieId);
    }
}
