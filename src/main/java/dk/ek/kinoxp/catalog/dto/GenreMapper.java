package dk.ek.kinoxp.catalog.dto;

import dk.ek.kinoxp.catalog.model.Actor;
import dk.ek.kinoxp.catalog.model.Genre;

public class GenreMapper {

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(
                genre.getGenre_id(),
                genre.getName()
        );
    }

    public static Genre toEntity(GenreDto genreDto) {
        Genre genre = new Genre();
        genre.setGenre_id(genreDto.genre_id());
        genre.setName(genreDto.name());
        return genre;
    }
}
