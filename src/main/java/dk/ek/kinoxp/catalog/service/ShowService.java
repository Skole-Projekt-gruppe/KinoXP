package dk.ek.kinoxp.catalog.service;

import dk.ek.kinoxp.catalog.dto.MapperShow;
import dk.ek.kinoxp.catalog.dto.ShowDto;
import dk.ek.kinoxp.catalog.exception.NotFoundException;
import dk.ek.kinoxp.catalog.model.Show;
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

    public List<ShowDto> getAllShow(){
        List<Show> shows = showRepository.findAll();

        //if (shows.isEmpty()){
        //   throw new NotFoundException("No shows found");
        // }

        List<ShowDto> showDtos = new ArrayList<>();
        for(var show : shows){
            showDtos.add(MapperShow.toDto(show));
        }
        return showDtos;
    }
}
