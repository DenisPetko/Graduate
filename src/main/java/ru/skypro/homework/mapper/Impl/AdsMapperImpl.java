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
        adsDto.setPk(ads.getId());
        adsDto.setPrice(ads.getPrice());
        adsDto.setTitle(ads.getTitle());
        return adsDto;
    }

    @Override
    public Ads mapAdsDtoToAds(AdsDto adsDto) {
        Ads ads = new Ads();
        ads.setAuthor(adsDto.getAuthor());
        ads.setImage(adsDto.getImage());
        ads.setId(adsDto.getPk());
        ads.setPrice(adsDto.getPrice());
        ads.setTitle(adsDto.getTitle());
        return ads;
    }


    @Override
    public FullAdsDto mapAdsToFullAdsDto(Ads ads) {
        FullAdsDto fullAdsDto = new FullAdsDto();
        fullAdsDto.setPk(ads.getId()); //todo
        fullAdsDto.setAuthorFirstName(ads.getAuthor().getFirstName());
        fullAdsDto.setAuthorLastName(ads.getAuthor().getLastName());
        fullAdsDto.setEmail(ads.getAuthor().getEmail());
        fullAdsDto.setPhone(ads.getAuthor().getPhone());
        fullAdsDto.setTitle(ads.getTitle());
        fullAdsDto.setDescription(ads.getDescription());
        fullAdsDto.setImage("/ads/" + ads.getImage() + "/image"); //todo
        fullAdsDto.setPrice(ads.getPrice());
        return fullAdsDto;
    }

    @Override
    public Ads mapCreatedAdsDtoToAds(CreateAdsDto createAdsDto) {
        Ads ads = new Ads();
        ads.setTitle(createAdsDto.getTitle());
        ads.setPrice(createAdsDto.getPrice());
        return ads;
    }

    @Override
    public Collection<AdsDto> mapAdsListToAdsDtoList(Collection<Ads> adCollection) {
        return null;
    }
}
