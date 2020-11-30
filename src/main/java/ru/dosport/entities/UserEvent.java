package ru.dosport.entities;

import lombok.Getter;
import ru.dosport.dto.SportTypeDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Entity
public class UserEvent {
    // статус юзера в мероприятии: точно идет, неточно
    @Column(name = "status", nullable = false)
    private String status;

    // id мероприятия
    @Id
    @Column(name = "event_id", nullable = false)
    private Long eventId;

    // Дата проведения мероприятия
    @Column(name = "date", nullable = false)
    private LocalDate dateEvent;

    // Время начала мероприятия
    @Column(name = "start_time", nullable = false)
    private LocalTime startTimeEvent;

    // Время завершения мероприятия
    @Column(name = "end_time")
    private LocalTime endTimeEvent;

    // Вид спорта
    @Column(name = "title", nullable = false)
    private String sportType;

    // Игровая площадка
    @ManyToOne
    @JoinColumn(name = "sportground_id", nullable = false)
    private SportGround sportGround;

    // ID чата
    @Column(name = "chat_id")
    private Long chatId;
}
