package ru.dosport.mappers;

import org.mapstruct.*;
import ru.dosport.dto.EventDto;
import ru.dosport.dto.UserEventDto;
import ru.dosport.entities.Event;
import ru.dosport.entities.UserEvent;

import java.util.List;

/**
 * Маппер, преобразующий классы Event и EventDto друг в друга
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {SportTypeMapper.class, EventMemberMapper.class})
public interface EventMapper {

    @Mappings({
            @Mapping(target = "eventId", source = "entity.id"),
            @Mapping(target = "sportGroundId", source = "entity.sportGround.id"),
            @Mapping(target = "members", source = "entity.members"),
            @Mapping(target = "organizerId", source = "entity.organizerId")
    })
    EventDto mapEntityToDto(Event entity);

    List<EventDto> mapEntityToDto(List<Event> entities);

    @Mappings({
            @Mapping(target = "id", source = "dto.eventId")
    })
    Event mapDtoToEntity(EventDto dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "sportGround", ignore = true),
            @Mapping(target = "organizerId", ignore = true),
            @Mapping(target = "members", ignore = true),
            @Mapping(target = "creationDateTime", ignore = true),
    })
    Event update(@MappingTarget Event entity, EventDto dto);

    @Mappings({
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "eventId", source = "entity.eventId", ignore = true),
            @Mapping(target = "chatId", source = "entity.chatId", ignore = true),
            @Mapping(target = "sportType", source = "entity.sportType", ignore = true),
            @Mapping(target = "sportGroundId", source = "entity.sportGround", ignore = true)
    })
    UserEventDto mapUserEventToUserEventDto(UserEvent entity);

    List<UserEventDto> mapUserEventToUserEventDto(List<UserEvent> entity);
}
