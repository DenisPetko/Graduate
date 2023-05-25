package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@Tag(name = "Регистрация", description = "API - Регистрация пользователя")
public class RegisterController {

    @Operation(summary = "Регистрация пользователя")
    @ApiResponse(
            responseCode = "404",
            description = "Not Found"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Created"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @PostMapping
    public ResponseEntity<?> register() {
//todo:
        return ResponseEntity.ok().build();
    }
}
