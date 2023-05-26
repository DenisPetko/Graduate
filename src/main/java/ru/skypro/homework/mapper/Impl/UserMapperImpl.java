package ru.skypro.homework.mapper.Impl;


import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.RegisterReqDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;

@Component
public class UserMapperImpl implements UserMapper {

    public UserDTO mapToUserDTO(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        return userDto;
    }

    public User mapToUser(UserDTO userDto) {
        User mappedUser = new User();
        mappedUser.setId(userDto.getId());
        mappedUser.setEmail(userDto.getEmail());
        mappedUser.setFirstName(userDto.getFirstName());
        mappedUser.setLastName(userDto.getLastName());
        mappedUser.setPhone(userDto.getPhone());
        return mappedUser;
    }

    @Override
    public User mapToUser(RegisterReqDTO registerReqDto) {
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
