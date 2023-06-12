package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.service.CommentService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/ads/{id}/comments")
    public ResponseEntity<CommentDto> getComment(@PathVariable long id) {
        return ResponseEntity.ok().build();
    }
}
