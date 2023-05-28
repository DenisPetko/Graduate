package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class ResponseWrapperCommentDto {
    private int count;
    private Collection<CommentDto> results;
}
