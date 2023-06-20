package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class FullAdsDto {
    private int pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private int price;
    private String title;
}
