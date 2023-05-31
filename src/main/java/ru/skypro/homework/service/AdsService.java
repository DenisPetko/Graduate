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

    AdsDto addAd(CreateAdsDto adDto, MultipartFile image);

    FullAdsDto getFullAd(long id);

    boolean removeAdDto(Integer id);

    AdsDto updateAdDto(Integer id, CreateAdsDto adDto);

    ResponseWrapperAdsDto getAdsMe();

    void updateImageAdDto(Integer id, MultipartFile image);
}
