package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User with username " + username + " doesn't exists"));
    }

    @Override
    public Optional<User> findAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByEmail(currentPrincipalName);
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
}
