package dk.ek.kinoxp.catalog.dto;

import dk.ek.kinoxp.catalog.model.Teather;

public class MapperTeather
{
    public static TeatherDto toDto(Teather teather){
        return new TeatherDto(
                teather.getTeather_id(),
                teather.getName()
        );
    }

    public static Teather toEntity(TeatherDto teatherDto){
        Teather teather = new Teather();
        teather.setTeather_id(teatherDto.teather_id());
        teather.setName(teatherDto.name());

        return teather;
    }
}
