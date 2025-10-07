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
}
