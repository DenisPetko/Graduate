package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.model.Ads;

import java.util.Collection;

public interface AdsMapper {
    AdsDto mapAdsToAdsDto(Ads ads);
    Ads mapAdsDtoToAds(AdsDto adsDto);
    FullAdsDto mapAdsToFullAdsDto(Ads ads);
    Ads mapCreatedAdsDtoToAds(CreateAdsDto createAdsDto);
    Collection<AdsDto> mapAdsListToAdsDtoList(Collection<Ads> adCollection);
}
