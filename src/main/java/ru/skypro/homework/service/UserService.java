package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findAuthUser();
    UserDto getUserDto();
    UserDto updateUserDto(UserDto newUserDto);
    void updateUserImage(MultipartFile image);
    void updateUserPassword(NewPasswordDto passwordDto);

    void setNewAdminUser(int idOfUser);
}
