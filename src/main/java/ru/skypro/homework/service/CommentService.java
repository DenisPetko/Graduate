package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;

/**
 * Интерфейс для работы с Comments
 */
public interface CommentService {
    ResponseWrapperCommentDto getComments(long adsId);
    CommentDto addComment(long adId, String commentDto);
    boolean deleteComment(long adsId, long commentId);
    CommentDto updateComment(long adsId, long commentId, CommentDto commentDto);
}
