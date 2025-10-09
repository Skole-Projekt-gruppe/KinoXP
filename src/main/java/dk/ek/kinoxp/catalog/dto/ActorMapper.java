package dk.ek.kinoxp.catalog.dto;

import dk.ek.kinoxp.catalog.model.Actor;

public class ActorMapper {

    public static ActorDto toDto(Actor actor) {
        return new ActorDto(
                actor.getActor_id(),
                actor.getName()
        );
    }

    public static Actor toEntity(ActorDto actorDto) {
        Actor actor = new Actor();
        actor.setActor_id(actorDto.actor_id());
        actor.setName(actorDto.name());
        return actor;
    }
}
