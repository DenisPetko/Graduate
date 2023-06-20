package ru.skypro.homework.mapper.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Comment;

import java.time.ZoneId;
@Component
@RequiredArgsConstructor
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentDto mapToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setPk(comment.getId());
        commentDto.setAuthor(comment.getAuthor().getId());
        commentDto.setCreatedAt(comment.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        commentDto.setText(comment.getText());
        commentDto.setAuthorFirstName(comment.getAuthorFirstName());
        if (comment.getAuthor().getImage() != null) {
            commentDto.setAuthorImage("/users/" + comment.getImage().getId() + "/image");
        }
        return commentDto;
    }

    @Override
    public Comment mapToComment(CommentDto commentDto) {
        Comment mappedComment = new Comment();
        mappedComment.setText(commentDto.getText());
        return mappedComment;
    }
}
