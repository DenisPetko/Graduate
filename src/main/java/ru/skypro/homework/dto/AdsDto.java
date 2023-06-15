package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class AdsDto {
    private int author;
    private String image;
    private int pk;
    private int price;
    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdsDto adsDto = (AdsDto) o;
        return author == adsDto.author && pk == adsDto.pk && price == adsDto.price && Objects.equals(image, adsDto.image) && Objects.equals(title, adsDto.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, image, pk, price, title);
    }
}
