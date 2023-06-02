package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    @ManyToOne
    @JoinColumn(name = "ads_id", nullable = false)
    private Ads ads;
    @Column(name = "author_image_id")
    private long authorImage;
    @Column(name = "author_first_name")
    private String authorFirstName;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column
    private String text;

}
