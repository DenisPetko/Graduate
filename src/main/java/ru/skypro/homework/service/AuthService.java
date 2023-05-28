package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.model.Role;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReqDto registerReqDTO, Role role);
    void changePassword(NewPasswordDto newPasswordDto);
}
