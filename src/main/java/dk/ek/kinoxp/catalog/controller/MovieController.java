package dk.ek.kinoxp.catalog.controller;

import dk.ek.kinoxp.catalog.dto.MovieDto;
import dk.ek.kinoxp.catalog.model.Movie;
import dk.ek.kinoxp.catalog.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        try {
            List<MovieDto> movieDtos = movieService.getAllMovies();
            return ResponseEntity.ok(movieDtos);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        try {
            MovieDto movie = movieService.getMovieById(id);
            return ResponseEntity.ok(movie);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
