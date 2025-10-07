package dk.ek.kinoxp.catalog.service;

import dk.ek.kinoxp.catalog.dto.Mapper;
import dk.ek.kinoxp.catalog.dto.MovieDto;
import dk.ek.kinoxp.catalog.exception.NotFoundException;
import dk.ek.kinoxp.catalog.model.Movie;
import dk.ek.kinoxp.catalog.repository.MovieRepository;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        if (movies.isEmpty()) {
            throw new NotFoundException("No movies found");
        }

        List<MovieDto> movieDtos = new ArrayList<>();
        for(var movie : movies) {
            movieDtos.add(Mapper.toDto(movie));
        }
        return movieDtos;
    }

    public MovieDto getMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isEmpty()) {
            throw new NotFoundException("No movie found with id: " + id);
        }
        return Mapper.toDto(movie.get());
    }
}
