package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AdsNotFoundException extends RuntimeException {
    public AdsNotFoundException() {
        super("Объявление не найдено");
    }
}
