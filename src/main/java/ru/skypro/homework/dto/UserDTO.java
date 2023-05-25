package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int userID;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String image;

}
