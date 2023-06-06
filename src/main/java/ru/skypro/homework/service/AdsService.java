package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;

/**
 * Интерфейс для работы с Ads
 */
public interface AdsService {

    ResponseWrapperAdsDto getAllAdsDto();

    AdsDto addAds(CreateAdsDto createAdsDto, MultipartFile image);

    FullAdsDto getFullAds(long id);

    boolean removeAdsDto(long id);

    AdsDto updateAdsDto(long id, CreateAdsDto adDto);

    ResponseWrapperAdsDto getAllAdsMe();

    void updateImageAdsDto(long id, MultipartFile image);

}
