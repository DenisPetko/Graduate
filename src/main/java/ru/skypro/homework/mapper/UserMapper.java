package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;


public interface UserMapper {

    UserDto mapToUserDTO(User user);


    User mapToUser(UserDto userDto);


    User mapToUser(RegisterReqDto registerReqDto);
}
