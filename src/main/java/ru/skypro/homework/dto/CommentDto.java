package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long pk; //  'id комментария'
    private User author;
    private String authorImage;
    private String authorFirstName;
    private long createdAt; //  description: 'дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970'
    private String text;
}
