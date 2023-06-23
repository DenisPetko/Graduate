package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывающий объявление
 **/

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ads")
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @JsonIgnore
    private User author;
    @OneToOne (cascade = CascadeType.ALL, orphanRemoval = true) //cascade = удаление, все, что было связано(существуют разные типы)
    @JoinColumn(name = "image_id")
    @JsonIgnore
    private Image image;
    private int price;
    private String title;
    private String description;
    @OneToMany(mappedBy = "ads", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

}
