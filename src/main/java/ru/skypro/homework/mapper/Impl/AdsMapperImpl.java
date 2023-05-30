package ru.skypro.homework.mapper.Impl;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.model.Ads;

import java.util.Collection;

public class AdsMapperImpl implements AdsMapper {
    @Override
    public AdsDto mapAdsToAdsDto(Ads ads) {
        AdsDto adsDto = new AdsDto();
        adsDto.setAuthor(ads.getAuthor());
        adsDto.setImage(ads.getImage());
        adsDto.setPk(ads.getPk());
        adsDto.setPrice(ads.getPrice());
        adsDto.setTitle(ads.getTitle());
        return adsDto;
    }

    @Override
    public Ads mapAdsDtoToAds(AdsDto adsDto) {
        Ads ads = new Ads();
        ads.setAuthor(adsDto.getAuthor());
        ads.setImage(adsDto.getImage());
        ads.setPk(adsDto.getPk());
        ads.setPrice(adsDto.getPrice());
        ads.setTitle(adsDto.getTitle());
        return ads;
    }


    @Override
    public FullAdsDto mapAdsToFullAdsDto(Ads ads) {
        return null;
    }

    @Override
    public Ads mapCreatedAdsDtoToAds(CreateAdsDto createAdsDto) {
        return null;
    }

    @Override
    public Collection<AdsDto> mapAdsListToAdsDtoList(Collection<Ads> adCollection) {
        return null;
    }
}
