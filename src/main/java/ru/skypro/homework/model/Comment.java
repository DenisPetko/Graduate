package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ads_id", nullable = false)
    private Ads ads;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column
    private String text;
    @OneToOne()
    @JsonIgnore
    @JoinColumn(name = "author_image_id")
    private Image image;
    @JoinColumn(name = "author_first_name")
    private String authorFirstName;

}
