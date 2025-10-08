package dk.ek.kinoxp.catalog.dto;

import dk.ek.kinoxp.catalog.model.Movie;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static MovieDto toDto(Movie movie) {

        List<ActorDto> actors = new ArrayList<>();

        for (var actor : movie.getActors()) {
            actors.add(ActorMapper.toDto(actor));
        }

        List<GenreDto> genres = new ArrayList<>();

        for (var genre : movie.getGenres()) {
            genres.add(GenreMapper.toDto(genre));
        }

        return new MovieDto(
                movie.getMovie_id(),
                movie.getTitle(),
                movie.getPoster(),
                movie.getDuration_min(),
                movie.getAge_limit(),
                movie.getStart_date(),
                movie.getEnd_date(),
                actors,
                genres
        );
    }

    public static Movie toEntity(MovieDto movieDto) {
        Movie movie = new Movie();

        movie.setMovie_id(movieDto.movie_id());
        movie.setTitle(movieDto.title());
        movie.setPoster(movieDto.poster());
        movie.setDuration_min(movieDto.duration_min());
        movie.setAge_limit(movieDto.age_limit());
        movie.setStart_date(movieDto.start_date());
        movie.setEnd_date(movieDto.end_date());

        for (var actorDto : movieDto.actors()) {
            var actor = ActorMapper.toEntity(actorDto);
            movie.addActor(actor);
        }

        for (var genreDto : movieDto.genres()) {
            var genre = GenreMapper.toEntity(genreDto);
            movie.addGenre(genre);
        }
        return movie;
    }

}
