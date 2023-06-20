package ru.skypro.homework.mapper.Impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.ImageRepository;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    private final ImageRepository imageRepository;
    @Override
    public UserDto mapToUserDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        if (user.getImage() != null) {
            userDto.setImage("/users/" + user.getImage().getId() + "/image");
        }
        return userDto;
    }

    @Override
    public User mapToUser(UserDto userDto) {
        User mappedUser = new User();
        userDto.setId(userDto.getId());
        mappedUser.setFirstName(userDto.getFirstName());
        mappedUser.setLastName(userDto.getLastName());
        mappedUser.setPhone(userDto.getPhone());
        mappedUser.setImage(imageRepository.getReferenceById(userDto.getImage()));
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
        return mappedUser;
    }
}
