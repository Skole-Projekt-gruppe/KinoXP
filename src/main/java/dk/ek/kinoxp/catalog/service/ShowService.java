package dk.ek.kinoxp.catalog.service;

import dk.ek.kinoxp.catalog.dto.MapperShow;
import dk.ek.kinoxp.catalog.dto.ShowDto;
import dk.ek.kinoxp.catalog.exception.NotFoundException;
import dk.ek.kinoxp.catalog.model.Movie;
import dk.ek.kinoxp.catalog.model.Show;
import dk.ek.kinoxp.catalog.model.Teather;
import dk.ek.kinoxp.catalog.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService
{
    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository){
        this.showRepository = showRepository;
    }

    // Henter alle shows fra databasen og mapper dem til DTO'er
    public List<ShowDto> getAllShow()
    {
        List<Show> shows = showRepository.findAll();
        List<ShowDto> showDtos = new ArrayList<>();
        for (Show show : shows) {
            showDtos.add(MapperShow.toDto(show));
        }
        return showDtos; // returnerer listen af DTO'er
    }


    // Henter ét show ud fra ID og mapper til DTO
    public ShowDto getShowById(Long id) {
        Show show = showRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Show not found with id: " + id));
        return MapperShow.toDto(show);
    }

    public ShowDto create(ShowDto dto){
        if (dto.start_time().compareTo(dto.end_time()) >=0) {
            throw new IllegalArgumentException("Start time must be before end time");
        }

        Movie movieRef = new Movie();
        movieRef.setMovie_id(dto.movie().movie_id());

        Teather teatherRef = new Teather();
        teatherRef.setTeather_id(dto.teather().teather_id());

        // Bygger fra entiteten fra DTO
        Show entity = new Show();
        entity.setPlanned_at(dto.planned_at());
        entity.setStart_time(dto.start_time());
        entity.setEnd_time(dto.end_time());
        entity.setMovie(movieRef);
        entity.setTeather(teatherRef);

        Show saved = showRepository.save(entity);
        return MapperShow.toDto(saved);
    }

    // Opdaterer et show ud fra DTO
    public ShowDto update(ShowDto dto) {
        // Finder show i databasen - smider fejl, hvis den ikke findes
        Show show = showRepository.findById(dto.show_id())
                .orElseThrow(() -> new NotFoundException("Show not found with id: " + dto.show_id()));

        // Mapper de felter der må ændres
        MapperShow.updateEntityFromDto(dto, show);

        // Gemmer ændringer i databasen
        Show updatedShow = showRepository.save(show);

        // Returnerer det opdaterede show som DTO til controlleren
        return MapperShow.toDto(updatedShow);
    }
}