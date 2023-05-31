package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class ResponseWrapperAdsDto {
    private int count;
    private Collection<AdsDto> results;

    public ResponseWrapperAdsDto(Collection<AdsDto> results) {
        this.count = results.size();
        this.results = results;
    }
}
