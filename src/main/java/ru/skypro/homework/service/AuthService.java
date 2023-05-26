package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.RegisterReqDTO;
import ru.skypro.homework.model.Role;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReqDTO registerReqDTO, Role role);
    void changePassword(NewPasswordDTO newPasswordDto);
}
