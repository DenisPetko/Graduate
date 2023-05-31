package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;

/**
 * Интерфейс для работы с Comments
 */
public interface CommentService {
    ResponseWrapperCommentDto getComments(Integer adsId);
    CommentDto addComment(Integer adId, CommentDto commentDto);
    boolean deleteComment(long adsId, long commentId);
    CommentDto updateComment(long adsId, long commentId);
}
