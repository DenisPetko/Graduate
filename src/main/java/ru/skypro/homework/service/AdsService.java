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

    /**
     * Метод ищет и возвращает список всех объявлений
     */
    ResponseWrapperAdsDto getAllAdsDto();

    /**
     * Метод создает объявление
     */
    AdsDto addAd(CreateAdsDto adDto, MultipartFile image);

    /**
     * Метод ищет и возвращает объявление по id
     */
    FullAdsDto getAd(Integer id);
}
