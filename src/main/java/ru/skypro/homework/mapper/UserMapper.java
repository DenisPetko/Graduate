package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.RegisterReqDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.User;


public interface UserMapper {

    UserDTO mapToUserDTO(User user);


    User mapToUser(UserDTO userDto);


    User mapToUser(RegisterReqDTO registerReqDto);
}
