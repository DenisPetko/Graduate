package ru.skypro.homework.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.config.WebSecurityConfig;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.service.AdsService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {WebSecurityConfig.class})
class AdsControllerTest {
    @Mock
    private AdsService adsService;

    @InjectMocks
    AdsController adsController;

    private final CreateAdsDto createAdsDto = new CreateAdsDto();

    @BeforeEach
    public void setUp() {
        createAdsDto.setDescription("test description");
        createAdsDto.setTitle("test title");
        createAdsDto.setPrice(300);
    }

    @Test
    void shouldReturnAllAds() {
        List<AdsDto> adsDtoList = Collections.singletonList(new AdsDto());
        ResponseWrapperAdsDto adsDto = new ResponseWrapperAdsDto(adsDtoList);

        when(adsService.getAllAdsDto()).thenReturn(adsDto);

        ResponseEntity<ResponseWrapperAdsDto> responseEntity = adsController.getAllAds();

        verify(adsService).getAllAdsDto();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void shouldAddAd() {
        AdsDto adsDto = new AdsDto();
        MultipartFile image = new MockMultipartFile("test.jpg", "test.jpg", "image/jpg",
                "test image".getBytes());
        when(adsService.addAds(createAdsDto, image)).thenReturn(adsDto);

        ResponseEntity<AdsDto> response = adsController.addAd(createAdsDto, image);

        verify(adsService).addAds(createAdsDto, image);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(adsDto, response.getBody());
        assertNotNull(adsDto);
    }

    @Test
    void getComments() {
    }

    @Test
    void addComment() {
    }

    @Test
    void getAds() {
    }

    @Test
    void removeAds() {
    }

    @Test
    void updateAds() {
    }

    @Test
    void deleteComment() {
    }

    @Test
    void updateComment() {
    }

    @Test
    void getAdsMe() {
    }

    @Test
    void updateImage() {
    }

    @Test
    void getImage() {
    }
}