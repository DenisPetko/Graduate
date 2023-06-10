package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException() {
        super("Image is not found");
    }
}
