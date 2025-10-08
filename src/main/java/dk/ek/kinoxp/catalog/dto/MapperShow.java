package dk.ek.kinoxp.catalog.dto;

import dk.ek.kinoxp.catalog.model.Show;

public class MapperShow
{

    public static ShowDto toDto(Show show){
        return new ShowDto(
                show.getShow_id(),
                show.getPlanned_at(),
                show.getStart_time(),
                show.getEnd_time(),
                Mapper.toDto(show.getMovie()),
                MapperTeather.toDto(show.getTeather())
        );
    }

    public static Show toEntity(ShowDto showDto){
        Show show = new Show();
        show.setShow_id(showDto.show_id());
        show.setPlanned_at(showDto.planned_at());
        show.setStart_time(showDto.start_time());
        show.setEnd_time(showDto.end_time());
        show.setMovie (Mapper.toEntity(showDto.movie()));
        show.setTeather (MapperTeather.toEntity(showDto.teather()));

        return show;
    }

    public  static void updateEntityFromDto(ShowDto dto, Show entity){
        // Opdater KUN felter, som må ændres fra UI
        // (så vi undgår at overskrive fx movie eller teater utilsigtet)

        if (dto.planned_at() != null) {
            entity.setPlanned_at(dto.planned_at());
        }
        if (dto.start_time() != null) {
            entity.setStart_time(dto.start_time());
        }
        if (dto.end_time() != null) {
            entity.setEnd_time(dto.end_time());
        }
        if (dto.movie() != null && dto.movie().title() != null && entity.getMovie() != null) {
            entity.getMovie().setTitle(dto.movie().title());
        }
        if (dto.teather() != null && dto.teather().name() != null && entity.getTeather() != null) {
            entity.getTeather().setName(dto.teather().name());
        }

    }

}
