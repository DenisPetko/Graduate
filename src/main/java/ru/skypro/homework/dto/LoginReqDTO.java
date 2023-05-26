package ru.skypro.homework.dto;

import lombok.Data;
/**
 * Класс DTO для передачи данных при авторизации пользователя
 */
@Data
public class LoginReqDTO {
    private String password;
    private String username;

}
