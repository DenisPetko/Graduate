package ru.skypro.homework.model;

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
    @Column(name = "author")
    private int author;
    @Column(name = "image")
    private String image;
    @Column(name = "pk")
    private int pk; //id объявления
    @Column(name = "price")
    private int price;
    @Column(name = "title")
    private String title;

}
