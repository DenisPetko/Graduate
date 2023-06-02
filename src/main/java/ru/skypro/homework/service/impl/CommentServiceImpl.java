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
        List<Comment> commentList = commentRepository.findAll();
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            if (adsId == comment.getAds().getId()) {
                commentDtoList.add(commentMapper.mapToCommentDto(comment));
            }
        }
        return new ResponseWrapperCommentDto(commentDtoList);
    }

    @Override
    public CommentDto addComment(long adsId, CommentDto commentDto) {
        Ads ads = adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new);
        Comment comment = commentMapper.mapToComment(commentDto);
        User user = userService.findAuthUser().orElseThrow(UserNotFoundException::new);
        comment.setAuthor(user);
        comment.setAuthorFirstName(commentDto.getAuthorFirstName());
        comment.setAds(ads);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setText(commentDto.getText());
        return commentDto;
    }

    @Override
    public boolean deleteComment(long adsId, long commentId) {
        Optional<User> user = userService.findAuthUser();
        if (user.isPresent()) {
            commentRepository.deleteById(commentId);
        }
        return true;
    }

    @Override
    public CommentDto updateComment(long adsId, long commentId) {
        adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new);
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        return commentMapper.mapToCommentDto(comment);
    }
}
