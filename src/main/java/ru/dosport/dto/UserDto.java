package ru.dosport.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

import static ru.dosport.helpers.Messages.*;

/**
 * Dto представление сущности Пользователь
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Dto представление сущности Пользователь")
public class UserDto {

    @NotBlank(message = DATA_NOT_BLANK)
    @Positive
    @ApiModelProperty(notes = "Уникальный идентификатор пользователя, больше 0",
            dataType = "Long", example = "1", required = true, position = 0)
    private Long id;

    @Size(min=4, max=50, message = INVALID_USERNAME_LENGTH)
    @NotBlank(message = DATA_NOT_BLANK + "Никнейм")
    @ApiModelProperty(notes = "Никнейм, от 4 до 50 символов",
            dataType = "String", example = "Nickname", required = true, position = 1)
    private String username;

    @ApiModelProperty(notes = "Дата рождения пользователя в формате dd-MM-yyyy",
            dataType = "String", example = "31-01-2001",  position = 2)
    private String birthdayDate;

    @NotBlank(message = DATA_NOT_BLANK + "Пол пользователя")
    @ApiModelProperty(notes = "Пол пользователя, значения: 'Не выбран', 'Женский', 'Мужской'",
            dataType = "String", example = "2", required = true, position = 3)
    private String gender;

    @ApiModelProperty(notes = "Личная информация о пользователе, до 150 символов",
            dataType = "String", example = "Информация о пользователе", position = 4)
    @Size(max=150)
    private String info;

    @ApiModelProperty(notes = "Ссылка на файл фото, до 150 символов",
            dataType = "String", example = "myphoto.png", position = 5)
    @Size(max = 150)
    private String photoLink;
}
