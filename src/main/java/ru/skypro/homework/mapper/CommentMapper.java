package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Comment;

public interface CommentMapper {
    CommentDto mapToCommentDto(Comment comment);

    Comment mapToComment(CommentDto commentDto);
}
