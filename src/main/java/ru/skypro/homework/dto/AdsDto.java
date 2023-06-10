package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.User;

@Data
public class AdsDto {
    private User author;
    private String image;
    private int pk;
    private int price;
    private String title;
}
