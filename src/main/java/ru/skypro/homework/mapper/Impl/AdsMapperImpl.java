package ru.skypro.homework.mapper.Impl;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.model.Ads;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class AdsMapperImpl implements AdsMapper {
    @Override
    public AdsDto mapAdsToAdsDto(Ads ads) {
        AdsDto adsDto = new AdsDto();
        adsDto.setAuthor((int) ads.getAuthor().getId());
        adsDto.setImage("/ads/" + ads.getImage().getId() + "/image");
        adsDto.setPk((int)ads.getId());
        adsDto.setPrice(ads.getPrice());
        adsDto.setTitle(ads.getTitle());
        return adsDto;
    }

    @Override
    public Ads mapAdsDtoToAds(AdsDto adsDto) {
        Ads ads = new Ads();
        ads.getAuthor().setId(adsDto.getAuthor());
        ads.getImage().setId(adsDto.getImage());
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
        ads.setDescription(createAdsDto.getDescription());
        ads.setPrice(createAdsDto.getPrice());
        return ads;
    }

    @Override
    public Collection<AdsDto> mapAdsListToAdsDtoList(Collection<Ads> adsCollection) {
        Collection<AdsDto> adsDtoCollection = new ArrayList<>(adsCollection.size());
        for (Ads ads : adsCollection) {
            adsDtoCollection.add(mapAdsToAdsDto(ads));
        }
        return adsDtoCollection;
    }
}
