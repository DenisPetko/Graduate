package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;

/**
 * Интерфейс для работы с Comments
 */
public interface CommentService {
    ResponseWrapperCommentDto getComments(int adsId);
    CommentDto addComment(int adId, String commentDto);
    boolean deleteComment(int adsId, int commentId);
    CommentDto updateComment(int adsId, int commentId, CommentDto commentDto);
}
