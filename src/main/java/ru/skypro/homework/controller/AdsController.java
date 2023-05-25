package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;

@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления", description = "API по работе с объявлениями")
public class AdsController {

    @Operation(summary = "Получить все объявления")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = ResponseWrapperAds[].class))
            )
    )
    @GetMapping
    public ResponseEntity<?> getAllAds() {
//todo:
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Добавить объявление")
    @ApiResponse(
            responseCode = "201",
            description = "Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Ads.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found"
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
    public ResponseEntity<?> addAd() {
//todo:
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получить комментарии объявления")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = ResponseWrapperComment[].class)))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found"
    )
    @Tag(name = "Комментарии")
    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getComments() {
//todo:
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Добавить комментарий к объявлению")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Comment.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found"
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
    public ResponseEntity<?> addComment() {
//todo:
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получить информацию об объявлении")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = FullAds.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found"
    )
    @PostMapping("/{id}")
    public ResponseEntity<?> getAds() {
//todo:
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить объявление")
    @ApiResponse(
            responseCode = "204",
            description = "No content"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd() {
//todo:
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновить информацию об объявлении")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Ads.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAds() {
//todo:
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить комментарий")
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @Tag(name = "Комментарии")
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment() {
//todo:
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновить комментарий")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Comment.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @Tag(name = "Комментарии")
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> updateComment() {
//todo:
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получить объявления авторизованного пользователя")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseWrapperAds[].class))
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @Tag(name = "Комментарии")
    @GetMapping("/me")
    public ResponseEntity<?> getAdsMe() {
//todo:
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновить картинку объявления")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE
    ))
    @ApiResponse(
            responseCode = "404",
            description = "Not Found"
    )
    @Tag(name = "Комментарии")
    @PatchMapping("/{id}/image")
    public ResponseEntity<?> updateImage() {
//todo:
        return ResponseEntity.ok().build();
    }
}
