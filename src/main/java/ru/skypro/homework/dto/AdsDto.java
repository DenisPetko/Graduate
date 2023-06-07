package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.User;

@Data
public class AdsDto {
    private User author;
    private long image;
    private long pk;
    private int price;
    private String title;
}
