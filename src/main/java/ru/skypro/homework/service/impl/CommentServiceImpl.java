package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AdsRepository adsRepository;
    private final UserService userService;

    @Override
    public ResponseWrapperCommentDto getComments(long adsId) {
        Ads ads = adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new);
        List<Comment> commentList = ads.getComments();
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentDtoList.add(commentMapper.mapToCommentDto(comment));
        }
        return new ResponseWrapperCommentDto(commentDtoList);
    }

    @Override
    public CommentDto addComment(long adsId, String commentStr) {
        Comment comment = new Comment();
        Ads ads = adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new);
        User user = userService.findAuthUser().orElseThrow(UserNotFoundException::new);
        comment.setAuthor(user);
        comment.setAds(ads);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setText(commentStr);
        CommentDto commentDto = commentMapper.mapToCommentDto(comment);
        return commentDto;
    }

    @Override
    public boolean deleteComment(long adsId, long commentId) {
        Optional<User> user = userService.findAuthUser();
        if (user.isPresent()) {
            commentRepository.deleteById(commentId);
            return true;
        }
        return false;
    }

    @Override
    public CommentDto updateComment(long adsId, long commentId, CommentDto commentDto) {
        adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new);
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        return commentMapper.mapToCommentDto(comment);
    }
}
