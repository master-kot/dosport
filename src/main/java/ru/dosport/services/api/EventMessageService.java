package ru.dosport.services.api;

import org.springframework.security.core.Authentication;
import ru.dosport.dto.*;

import java.util.List;

public interface EventMessageService {

    /**
     * Вернутть сообщение к мероприятию по его идентификатору
     *
     * @param id идентификатор мероприятия
     * @return сообщение к мероприятию
     */
    EventMessageDto getDtoById(Long id);

    /**
     * Вернуть все сообщения мероприятия
     *
     * @return список сообщений мероприятия
     */
    List<EventMessageDto> getAllDtoByEventId(Long eventId);

    /**
     * Создать новое сообщение к мероприятию
     *
     * @return новое сообщение к мероприятию, сохраненное в репозитории
     */
    EventMessageDto save(Long eventId, EventMessageRequest request, Authentication authentication);

    /**
     * Обновить сообщение к мероприятию
     * @param messageId id сообщения
     * @param eventId id мероприятия
     * @param request запрос с новым текстом
     * @param authentication
     * @return новое сообщение к мероприятию, сохраненное в репозитории
     */
    EventMessageDto update(Long messageId, Long eventId, EventMessageRequest request, Authentication authentication);

    /**
     * Удалить сообщение к мероприятию по его идентификатору
     *
     * @param id идентификатор сообщение
     * @param authentication
     * @return удалено ли сообщение
     */
    boolean deleteById(Long id, Authentication authentication);
}