package dk.ek.kinoxp.catalog.dto;

import dk.ek.kinoxp.catalog.model.Movie;

public class Mapper {

    public static MovieDto toDto(Movie movie) {
        return new MovieDto(
                movie.getMovie_id(),
                movie.getTitle(),
                movie.getPoster(),
                movie.getDuration_min(),
                movie.getAge_limit(),
                movie.getStart_date(),
                movie.getEnd_date()
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
        return movie;
    }

}
