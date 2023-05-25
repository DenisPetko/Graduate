package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ads {
    private int authorID;
    private String image;
    private int pk; //description: 'id объявления' ? openapi
    private int price;
    private String title;

}
