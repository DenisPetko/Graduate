package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    @Override
    public ResponseWrapperCommentDto getComments(Integer adsId) {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            if (adsId.equals(comment.getAds().getId())) {
                commentDtoList.add(commentMapper.mapToCommentDto(comment));
            }
        }
        return new ResponseWrapperCommentDto(commentDtoList);
    }

    @Override
    public CommentDto addComment(Integer adId, CommentDto commentDto) {
        return null;
    }

    @Override
    public boolean deleteComment(long adsId, long commentId) {
        return false;
    }

    @Override
    public CommentDto updateComment(long adsId, long commentId) {
        return null;
    }
}
