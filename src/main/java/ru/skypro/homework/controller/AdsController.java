package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.ImageService;


@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/")
@Tag(name = "Объявления", description = "API по работе с объявлениями")
@RequiredArgsConstructor
@Slf4j
public class AdsController {
    private final AdsService adsService;
    private final CommentService commentService;
    private final ImageService imageService;

    @Operation(summary = "Получить все объявления")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = ResponseWrapperAdsDto[].class))
            )
    )
    @GetMapping("/ads")
    public ResponseEntity<ResponseWrapperAdsDto> getAllAds() {
        return ResponseEntity.ok(adsService.getAllAdsDto());
    }

    @Operation(summary = "Добавить объявление")
    @ApiResponse(
            responseCode = "201",
            description = "Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AdsDto.class))
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
    @PostMapping(value = "/ads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAd(@RequestPart CreateAdsDto properties, @RequestPart MultipartFile image) {
        log.info("New ads added {}", properties.getTitle());
        return ResponseEntity.ok(adsService.addAds(properties, image));
    }

    @Operation(summary = "Получить комментарии объявления")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = ResponseWrapperCommentDto[].class)))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found"
    )
    @GetMapping("/ads/{adsId}/comments")
    public ResponseEntity<ResponseWrapperCommentDto> getComments(@PathVariable long adsId) {
        ResponseWrapperCommentDto comments = commentService.getComments(adsId); //todo: написать реализацию
        return ResponseEntity.ok().body(comments);
    }

    @Operation(summary = "Добавить комментарий к объявлению")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CommentDto.class))
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

    @PostMapping("/ads/{adsId}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable long adsId, @RequestBody String commentDto) {
        return ResponseEntity.ok(commentService.addComment(adsId, commentDto));
    }

    @Operation(summary = "Получить информацию об объявлении")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = FullAdsDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found"
    )
    @GetMapping("/ads/{adsId}")
    public ResponseEntity<FullAdsDto> getAds(@PathVariable long adsId) {
        return ResponseEntity.ok(adsService.getFullAds(adsId));
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

    @DeleteMapping("/ads/{adsId}")
    public ResponseEntity<?> removeAds(@PathVariable long adsId) {
        return ResponseEntity.ok(adsService.removeAdsDto(adsId));
    }

    @Operation(summary = "Обновить информацию об объявлении")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AdsDto.class))
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

    @PatchMapping("/ads/{adsId}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable long adsId, @RequestBody CreateAdsDto createAdsDto) {
        return ResponseEntity.ok(adsService.updateAdsDto(adsId, createAdsDto));
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
    @DeleteMapping("/ads/{adsId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable long adsId, @PathVariable long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(adsId, commentId));
    }

    @Operation(summary = "Обновить комментарий")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CommentDto.class))
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
    @PatchMapping("/ads/{adsId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long adsId, @PathVariable long commentId, @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateComment(adsId, commentId, commentDto));
    }

    @Operation(summary = "Получить объявления авторизованного пользователя")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseWrapperAdsDto[].class))
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @GetMapping("/ads/me")
    public ResponseEntity<ResponseWrapperAdsDto> getAdsMe() {
        return ResponseEntity.ok(adsService.getAllAdsMe());
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
    @PatchMapping(value = "/ads/{adsId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable long adsId, @RequestPart MultipartFile image) {
        adsService.updateImageAdsDto(adsId, image);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Получить картинку объявления",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content())
            })
    @GetMapping(value = "ads/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id){
        return ResponseEntity.ok(imageService.getImage(id));
    }


}
