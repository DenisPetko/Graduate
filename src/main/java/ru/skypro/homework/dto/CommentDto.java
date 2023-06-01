package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.User;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private long pk; //  'id комментария'
    private User author;
    private String authorImage;
    private String authorFirstName;
    private long createdAt; //  description: 'дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970'
    private String text;
}
