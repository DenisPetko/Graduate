package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;
import ru.skypro.homework.service.CommentService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @Operation(
            summary = "Получить комментарий",
            description = "Получить комментарий по id комментария",
            tags = "Комментарий"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = RegisterReqDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()}),
    }
    )
    @GetMapping("/ads/{id}/comments")
    public ResponseEntity<ResponseWrapperCommentDto> getComment(@PathVariable long id) {
        return ResponseEntity.ok().body(commentService.getComments(id));
    }
    @Operation(
            summary = "Добавить комментарий",
            description = "Добавление комментария к объявлению по айди, текст комментария",
            tags = "Комментарий"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = RegisterReqDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()}),
    }
    )
    @PostMapping("/ads/{id}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable long id, @RequestBody String commentDto) {
        //todo
        commentService.addComment(id, commentDto);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Удалить комментарий",
            description = "Удаление комментария по id объявления, айди комментария",
            tags = "Комментарий"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = RegisterReqDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content()}),
    }
    )
    @DeleteMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable long adId,@PathVariable long commentId) {
        commentService.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }
    @Operation(
            summary = "Обновить комментарий",
            description = "Удаление комментария по id объявления, айди комментария",
            tags = "Комментарий"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = RegisterReqDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content()}),
    }
    )
    @PatchMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long adId, @PathVariable long commentId, @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok().body(commentService.updateComment(adId, commentId, commentDto));
    }

}
