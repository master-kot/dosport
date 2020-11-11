package ru.dosport.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.dosport.dto.*;
import ru.dosport.services.api.EventService;
import ru.dosport.services.api.EventMessageService;

import javax.validation.Valid;

import java.util.List;

import static ru.dosport.helpers.Roles.ROLE_ADMIN;
import static ru.dosport.helpers.Roles.ROLE_USER;

/**
 * Контроллер Мероприятий.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    // Тип данных
    private final String DATA_TYPE = "application/json";

    // Необходимые сервисы
    private final EventService eventService;
    private final EventMessageService eventMessageService;

    @ApiOperation(value = "Отображает данные всех мероприятий")
    @GetMapping
    public ResponseEntity<List<EventDto>> readAllEvent() {
        return ResponseEntity.ok(eventService.getAllDto());
    }

    @ApiOperation(value = "Отображает данные мероприятия по его индексу")
    @GetMapping(value = "/{id}", produces = DATA_TYPE)
    public ResponseEntity<EventDto> readEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getDtoById(id));
    }

    @ApiOperation(value = "Создает новое мероприятие")
    @Secured(value = {ROLE_USER, ROLE_ADMIN})
    @PostMapping(produces = DATA_TYPE)
    public ResponseEntity<EventDto> createEvent(@Valid @RequestBody EventRequest eventRequest,
                                                Authentication authentication) {
        return ResponseEntity.ok(eventService.save(eventRequest, authentication));
    }

    @ApiOperation(value = "Изменяет данные мероприятия")
    @Secured(value = {ROLE_USER, ROLE_ADMIN})
    @PutMapping(value = "/{id}", produces = DATA_TYPE)
    public ResponseEntity<EventDto> updateEvent(@RequestBody EventDto eventDto, @PathVariable Long id,
                                                Authentication authentication) {
        return ResponseEntity.ok(eventService.update(eventDto, id, authentication));
    }

    @ApiOperation(value = "Удаляет мероприятие по его индексу")
    @Secured(value = {ROLE_USER, ROLE_ADMIN})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id, Authentication authentication) {
        return eventService.deleteById(id, authentication) ?
                ResponseEntity.badRequest().build() : ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Отображает данные всех участников мероприятия")
    @GetMapping("/{id}/members")
    public ResponseEntity<List<MemberDto>> readEventMember(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getAllMembers(id));
    }

    @ApiOperation(value = "Добавляет участника в мероприятие")
    @Secured(value = {ROLE_USER, ROLE_ADMIN})
    @PostMapping("/{id}/members")
    public ResponseEntity<?> addEventMember(@PathVariable Long id, @RequestBody MemberRequest request) {
        return eventService.createEventMember(id, request) != null ?
                ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @ApiOperation(value = "Отоброжает данные всех сообщений мероприятия")
    @Secured(value = {ROLE_USER, ROLE_ADMIN})
    @GetMapping("/{id}/messages")
    public ResponseEntity<List<EventMessageDto>> readBoard(@PathVariable Long id) {
        return ResponseEntity.ok(eventMessageService.getAllDtoByEventId(id));
    }

    @ApiOperation(value = "Добавляет сообщение к мероприятию")
    @Secured(value = {ROLE_USER, ROLE_ADMIN})
    @PostMapping("/{id}/messages")
    public ResponseEntity<EventMessageDto> createMessage(@PathVariable Long id,
                                                         @RequestBody EventMessageRequest request,
                                                         Authentication authentication) {
        return ResponseEntity.ok(eventMessageService.save(id, request, authentication));
    }

    @ApiOperation(value = "Обновляет данные сообщения")
    @Secured(value = {ROLE_USER, ROLE_ADMIN})
    @PutMapping("/{id}/message/{messageId}")
    public ResponseEntity<EventMessageDto> updateMessage(@PathVariable Long id, @PathVariable Long messageId,
                                                         EventMessageRequest request, Authentication authentication) {
        return ResponseEntity.ok(eventMessageService.update(messageId, id, request, authentication));
    }

    @ApiOperation(value = "Удаляет сообщение")
    @Secured(value = {ROLE_USER, ROLE_ADMIN})
    @DeleteMapping("/{id}/message/{messageId}")
    public ResponseEntity<EventMessageDto> deleteMessage(@PathVariable Long id, @PathVariable Long messageId,
                                                         Authentication authentication) {
        return eventMessageService.deleteById(id, authentication) ?
                ResponseEntity.badRequest().build() : ResponseEntity.ok().build();
    }
}
