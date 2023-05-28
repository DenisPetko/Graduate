package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.LoginReqDto;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.model.Role;
import ru.skypro.homework.service.AuthService;

import static ru.skypro.homework.model.Role.USER;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Регистрация пользователя",
            description = "Регистрация пользователя из тела запроса",
            tags = "Регистрация"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = RegisterReqDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content()}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content()})
    }
    )
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReqDto req) {
        log.info("New request for register:{},{}}",req.getUsername(), req.getRole());
        Role role = req.getRole() == null ? USER : req.getRole();
        if (authService.register(req, role)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(
            summary = "Авторизация пользователя",
            description = "Авторизация пользователя из тела запроса",
            tags = "Авторизация"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Пользователь авторизирован",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = LoginReqDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content()}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content()})
    }
    )
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqDto req) {
        if (authService.login(req.getUsername(), req.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}