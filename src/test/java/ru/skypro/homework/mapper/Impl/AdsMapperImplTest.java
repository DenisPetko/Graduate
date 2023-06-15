/*
package ru.skypro.homework.mapper.Impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.model.Ads;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AdsMapperImplTest {
    @InjectMocks
    AdsMapperImpl adsMapper;
    Ads ads = new Ads(1, );
    AdsDto adsDto = new AdsDto();

    @Test
    void mapAdsToAdsDto() {
        AdsDto adsDto = new AdsDto();
        AdsDto adsDtoFromMethod = adsMapper.mapAdsToAdsDto(ads);
        adsDto.setAuthor((int) ads.getAuthor().getId());
        adsDto.setImage("/ads/" + ads.getImage().getId() + "/image");
        adsDto.setPk((int)ads.getId());
        adsDto.setPrice(ads.getPrice());
        adsDto.setTitle(ads.getTitle());

        assertEquals(adsDto.getAuthor(), adsDtoFromMethod.getAuthor());
        assertEquals(adsDto.getImage(), adsDtoFromMethod.getImage());
        assertEquals(adsDto.getPk(), adsDtoFromMethod.getPk());
        assertEquals(adsDto.getPrice(), adsDtoFromMethod.getPrice());
        assertEquals(adsDto.getTitle(), adsDtoFromMethod.getTitle());
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
}
*/
