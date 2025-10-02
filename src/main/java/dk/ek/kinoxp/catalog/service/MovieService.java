package dk.ek.kinoxp.catalog.service;

import dk.ek.kinoxp.catalog.dto.MovieDto;
import dk.ek.kinoxp.catalog.model.Movie;
import dk.ek.kinoxp.catalog.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        if (movies.isEmpty()) {

        }
    }
}
