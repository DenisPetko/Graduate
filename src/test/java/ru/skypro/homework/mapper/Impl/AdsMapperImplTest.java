/*
package ru.skypro.homework.mapper.Impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.model.Ads;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdsMapperImplTest {
    @InjectMocks
    AdsMapperImpl adsMapper;

    @Mock
    Ads ads;

    @Test
    void mapAdsToAdsDto() {
        AdsDto adsDto = new AdsDto();
        adsDto.setAuthor((int) ads.getAuthor().getId());
        adsDto.setImage("/ads/" + ads.getImage().getId() + "/image");
        adsDto.setPk((int)ads.getId());
        adsDto.setPrice(ads.getPrice());
        adsDto.setTitle(ads.getTitle());

        assertEquals(adsDto.getAuthor(), adsMapper.mapAdsToAdsDto(ads).getAuthor());
    }

    @Test
    void mapAdsDtoToAds() {
    }

    @Test
    void mapAdsToFullAdsDto() {
    }

    @Test
    void mapCreatedAdsDtoToAds() {
    }

    @Test
    void mapAdsListToAdsDtoList() {
    }
}*/
