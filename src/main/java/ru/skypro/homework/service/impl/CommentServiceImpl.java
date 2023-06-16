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
import ru.skypro.homework.validation.ValidationForComments;

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
    private final ValidationForComments validation;

    @Override
    public ResponseWrapperCommentDto getComments(int adsId) {
        Ads ads = adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new);
        List<Comment> commentList = ads.getComments();
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentDtoList.add(commentMapper.mapToCommentDto(comment));
        }
        return new ResponseWrapperCommentDto(commentDtoList);
    }

    @Override
    public CommentDto addComment(int adsId, String commentStr) {
        Comment comment = new Comment();
        Ads ads = adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new);
        User user = userService.findAuthUser().orElseThrow(UserNotFoundException::new);
        comment.setAuthor(user);
        comment.setAds(ads);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setText(commentStr);
        comment.setAuthorFirstName(user.getFirstName());
        if (user.getImage() == null) {
        } else {
            comment.setImage(user.getImage());
        }
        CommentDto commentDto = commentMapper.mapToCommentDto(comment);
        commentRepository.save(comment);
        return commentDto;
    }

    @Override
    public boolean deleteComment(int adsId, int commentId) {
        Optional<User> user = userService.findAuthUser();
        if (validation.validateComments(user.get(), commentId)) {
            if (user.isPresent()) {
                commentRepository.deleteById(commentId);
                return true;
            }
        }
        return false;
    }

    @Override
    public CommentDto updateComment(int adsId, int commentId, CommentDto commentDto) {
        if (validation.validateComments(userService.findAuthUser().get(), commentId)) {
            adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new);
            Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
            comment.setText(commentDto.getText());
            commentRepository.save(comment);
            return commentMapper.mapToCommentDto(comment);
        }
        throw new CommentNotFoundException();
    }
}
