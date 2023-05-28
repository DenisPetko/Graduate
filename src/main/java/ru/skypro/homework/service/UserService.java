package ru.skypro.homework.service;

import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findAuthUser();
    UserDto getUserDto();
    UserDto updateUserDto(UserDto newUserDto);
}
