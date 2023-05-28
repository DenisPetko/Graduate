package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;

@RestController
@RequestMapping("/")
@Tag(name = "Пользователи", description = "API для работы с пользователями")
public class UserController {

    @Operation(summary = "Обновление пароля")
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found"
    )
    @PostMapping("/users/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        //todo
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получить информацию об авторизованном пользователе")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserDto.class))
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found"
    )
    @GetMapping("/users/me")
    public ResponseEntity<UserDto> getUser() {
//todo:
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновить информацию об авторизованном пользователе")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserDto.class))
    )
    @ApiResponse(
            responseCode = "204",
            description = "No Content"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found"
    )
    @PatchMapping("/users/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        //todo
        UserDto newUserDto = new UserDto(); // исправить!!!
        return ResponseEntity.ok().body(newUserDto);
    }


    @Operation(summary = "Обновить аватар авторизованного пользователя")
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found"
    )
    @PatchMapping("/users/me/image")
    public ResponseEntity<byte[]> updateUserImage(@RequestPart MultipartFile inputImage) {
        //todo
        return ResponseEntity.ok().build();
    }
}
