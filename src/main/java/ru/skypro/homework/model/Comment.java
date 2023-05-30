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
    @Column(name = "author")
    private int author;
    @Column(name = "authorImage")
    private String authorImage;
    @Column(name = "authorFirstName")
    private String authorFirstName;
    @Column(name = "createdAt")
    private int createdAt;
    @Column(name = "pk")
    private int pk; //id комментария
    @Column(name = "text")
    private String text;
}
