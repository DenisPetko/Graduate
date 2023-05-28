package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdsDto {
    private int author;
    private String image;
    private int pk; //description: 'id объявления' ? openapi
    private int price;
    private String title;
}
