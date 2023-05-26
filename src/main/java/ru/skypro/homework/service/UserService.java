package ru.skypro.homework.service;

import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findAuthUser();
    UserDTO getUserDto();
    UserDTO updateUserDto(UserDTO newUserDto);
}
