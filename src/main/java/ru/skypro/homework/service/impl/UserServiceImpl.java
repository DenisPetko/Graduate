package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final ImageService imageService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User with username " + username + " doesn't exists"));
    }

    @Override
    public Optional<User> findAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByUsername(currentPrincipalName);
    }

    @Override
    public UserDto getUserDto() {
        Optional<User> currentUser = findAuthUser();
        UserDto currentUserDto = new UserDto();
        if (currentUser.isPresent()) {
            currentUserDto = userMapper.mapToUserDTO(currentUser.get());
        }
        return currentUserDto;
    }

    @Override
    public UserDto updateUserDto(UserDto newUserDto) {
        Optional<User> currentUser = findAuthUser();
        User newCurrentUser = new User();
        if (currentUser.isPresent()) {
            newCurrentUser = currentUser.get();
            newCurrentUser.setFirstName(newUserDto.getFirstName());
            newCurrentUser.setLastName(newUserDto.getLastName());
            newCurrentUser.setPhone(newUserDto.getPhone());
            userRepository.save(newCurrentUser);
        }
        return userMapper.mapToUserDTO(newCurrentUser);
    }

    @Override
    public void updateUserImage(MultipartFile image) {
        log.info("New avatar {}", image.getName());
        User user = findAuthUser().get();
        Image newImage;
        if (userRepository.findByUsername(user.getUsername()).get().getImage() == null) {
            newImage = imageService.saveImage(image);
        } else {
            newImage = imageService.updateImage(image, user.getImage());
        }
        user.setImage(newImage);
        userRepository.save(user);
    }

    @Override
    public void updateUserPassword(NewPasswordDto passwordDto) {
        String encodes = encoder.encode(passwordDto.getNewPassword());
        userRepository.setNewPassword(encodes, findAuthUser().get().getId());
    }
}
