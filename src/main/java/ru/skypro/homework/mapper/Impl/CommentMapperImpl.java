package ru.skypro.homework.mapper.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.time.ZoneId;
@Component
@RequiredArgsConstructor
public class CommentMapperImpl implements CommentMapper {
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    public CommentDto mapToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setPk(comment.getId());
        commentDto.setAuthor(comment.getAuthor());
        commentDto.setCreatedAt(comment.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        commentDto.setText(comment.getText());
        commentDto.setAuthorImage("/users/" + commentDto.getAuthor().getImage().getId() + "/image");
        commentDto.setAuthorFirstName(comment.getAuthorFirstName());
        return commentDto;
    }

    public Comment mapToComment(CommentDto commentDto) {
        Comment mappedComment = new Comment();
        mappedComment.setText(commentDto.getText());
        return mappedComment;
    }
}
