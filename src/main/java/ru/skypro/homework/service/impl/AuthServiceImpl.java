package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.exception.UserUnauthorizedException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.Role;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UserServiceImpl manager;
  private final PasswordEncoder encoder;
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public boolean login(String userName, String password) {
    log.info("New login:{},{}}", userName);
    UserDetails userDetails = manager.loadUserByUsername(userName);
    String encryptedPassword = userDetails.getPassword();
    return encoder.matches(password, encryptedPassword);
  }

  @Override
  public boolean register(RegisterReqDto registerReqDTO, Role role) {
    log.info("New request for register:{},{}}",registerReqDTO.getUsername(), registerReqDTO.getRole());
    if (userRepository.findByUsername(registerReqDTO.getUsername()).isPresent()) {
      return false;
    }
    User regUser = userMapper.mapToUser(registerReqDTO);
    regUser.setRole(role);
    regUser.setPassword(encoder.encode(regUser.getPassword()));
    userRepository.save(regUser);
    return true;
  }


  @Override
  public void changePassword(NewPasswordDto newPasswordDto) {

    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();

    UserDetails principal = (UserDetails) authentication.getPrincipal();
    String currentEmail = principal.getUsername();

    User user = userRepository.findByUsername(currentEmail).orElseThrow(UserNotFoundException::new);
    UserDetails userDetails = manager.loadUserByUsername(user.getUsername());
    String encryptedPassword = userDetails.getPassword();
    if (encoder.matches(newPasswordDto.getCurrentPassword(), encryptedPassword)) {
      user.setPassword(encoder.encode(newPasswordDto.getNewPassword()));
      userRepository.save(user);
    } else {
      throw new UserUnauthorizedException();
    }
  }
}