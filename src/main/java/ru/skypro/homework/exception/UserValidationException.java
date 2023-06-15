package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UserValidationException extends RuntimeException{
    public UserValidationException() {
        super("User is not an owner, or having rule USER");
    }
}
