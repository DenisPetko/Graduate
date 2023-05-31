package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Класс, описывающий комментарий
 **/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @Column
    private int author;
    @Column
    private String authorImage;
    @Column
    private String authorFirstName;
    @Column
    private int createdAt;
    @Column
    private int pk; //id комментария
    @Column
    private String text;
}
