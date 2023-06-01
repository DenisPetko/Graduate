package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.User;

@Data
public class AdsDto {
    private User author;
    private String image;
    private long pk; //description: 'id объявления' ? openapi
    private int price;
    private String title;
}
