package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

@RestController
@RequestMapping("/")
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Пользователи", description = "API для работы с пользователями")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final ImageService imageService;

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
        userService.updateUserPassword(newPasswordDto);
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
        return ResponseEntity.ok().body(userService.getUserDto());
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
        userService.updateUserDto(userDto);
        return ResponseEntity.ok().body(userDto);
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
    @PatchMapping(value = "/users/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateUserImage(@RequestPart MultipartFile image) {
        userService.updateUserImage(image);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Получить картинку профиля",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content())
            })
    @GetMapping(value = "/users/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id){
        return ResponseEntity.ok(imageService.getImage(id));
    }
}
