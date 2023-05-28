package ru.skypro.homework.mapper.Impl;


import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;

@Component
public class UserMapperImpl implements UserMapper {

    public UserDto mapToUserDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
         return userDto;
    }

    public User mapToUser(UserDto userDto) {
        User mappedUser = new User();
        mappedUser.setId(userDto.getId());
        mappedUser.setEmail(userDto.getEmail());
        mappedUser.setFirstName(userDto.getFirstName());
        mappedUser.setLastName(userDto.getLastName());
        mappedUser.setPhone(userDto.getPhone());
        return mappedUser;
    }

    @Override
    public User mapToUser(RegisterReqDto registerReqDto) {
        User mappedUser = new User();
        mappedUser.setUsername(registerReqDto.getUsername());
        mappedUser.setPassword(registerReqDto.getPassword());
        mappedUser.setFirstName(registerReqDto.getFirstName());
        mappedUser.setLastName(registerReqDto.getLastName());
        mappedUser.setPhone(registerReqDto.getPhone());
        mappedUser.setRole(registerReqDto.getRole());
        mappedUser.setEmail(registerReqDto.getUsername());
        return mappedUser;
    }
}
