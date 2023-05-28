package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.UserDto;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000") //для взаимодействия фронта и бека
public class UserController {
    //Patch от Put отличается тем, что put заменяет обьект в БД полностью(все поля), а Patch - лишь частично
    @PatchMapping("/me")
    public UserDto updateUser(@RequestBody UserDto user) {
        System.out.println("Hello!");
        return new UserDto();
    }
}
