package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDto {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private int createdAt; //  description: 'дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970'
    private int pk; //  'id комментария'
    private String text;
}
