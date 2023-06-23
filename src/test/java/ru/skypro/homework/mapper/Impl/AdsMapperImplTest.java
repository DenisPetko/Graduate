package ru.skypro.homework.mapper.Impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdsMapperImplTest {

    private final AdsMapperImpl adsMapper = new AdsMapperImpl();

    private final Ads adsTest = new Ads();
    private final AdsDto adsDtoTest = new AdsDto();
    private final User user = new User();
    private final Image image = new Image();

    @BeforeEach
    public void setUp() {
        image.setId("image id");

        user.setId(123);

        adsTest.setId(1);
        adsTest.setAuthor(user);
        adsTest.setPrice(300);
        adsTest.setImage(image);
        adsTest.setTitle("ads test description");

        adsDtoTest.setAuthor(11);
        adsDtoTest.setImage("imageId");
        adsDtoTest.setPk(user.getId());
        adsDtoTest.setPrice(303);
        adsDtoTest.setTitle("adsDto test description");
    }

    @Test
    void mapAdsToAdsDto() {
        AdsDto adsDto = adsMapper.mapAdsToAdsDto(adsTest);

        assertEquals(adsDto.getPk(), adsTest.getId());
        assertEquals(adsDto.getAuthor(), adsTest.getAuthor().getId());
        assertEquals(adsDto.getPrice(), adsTest.getPrice());
        assertEquals(adsDto.getImage(), "/ads/" + adsTest.getImage().getId() + "/image");
        assertEquals(adsDto.getTitle(), adsTest.getTitle());
    }

}

