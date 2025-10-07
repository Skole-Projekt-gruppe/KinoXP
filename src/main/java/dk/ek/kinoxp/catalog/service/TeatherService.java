package dk.ek.kinoxp.catalog.service;


import dk.ek.kinoxp.catalog.dto.MapperTeather;
import dk.ek.kinoxp.catalog.dto.TeatherDto;
import dk.ek.kinoxp.catalog.exception.NotFoundException;
import dk.ek.kinoxp.catalog.model.Teather;
import dk.ek.kinoxp.catalog.repository.TeatherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeatherService
{
    private final TeatherRepository teatherRepository;

    public TeatherService(TeatherRepository teatherRepository){
        this.teatherRepository = teatherRepository;
    }

    public List<TeatherDto> getAllTeaters(){
        List<Teather> teaters = teatherRepository.findAll();{
            if (teaters.isEmpty()){
                throw new NotFoundException ("No teather found");
            }

            List<TeatherDto> teatherDtos = new ArrayList<>();{
            for(Teather teather : teaters)
                teatherDtos.add(MapperTeather.toDto(teather));
            }
            return teatherDtos;
        }
    }
}
