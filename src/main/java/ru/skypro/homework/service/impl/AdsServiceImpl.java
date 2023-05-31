package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.UserService;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsMapper adsMapper;
    private final AdsRepository adsRepository;
    private final UserService userService;

    @Override
    public ResponseWrapperAdsDto getAllAdsDto() {
        Collection<AdsDto> adsAll = adsMapper.mapAdsListToAdsDtoList(adsRepository.findAll());
        return new ResponseWrapperAdsDto(adsAll);
    }

    @Override
    public AdsDto addAd(CreateAdsDto adDto, MultipartFile image) {
        Ads newAds = adsMapper.mapCreatedAdsDtoToAds(adDto);
        newAds.setAuthor(userService.findAuthUser().orElseThrow(UserNotFoundException::new));
        newAds.setImage("pictures/Cottage.jpeg");
        adsRepository.save(newAds);
        return adsMapper.mapAdsToAdsDto(newAds);
    }

    @Override
    public FullAdsDto getFullAd(long id) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        return adsMapper.mapAdsToFullAdsDto(ads);
    }

    @Override
    public boolean removeAdDto(Integer id) {
        return false;
    }

    @Override
    public AdsDto updateAdDto(Integer id, CreateAdsDto adDto) {
        return null;
    }

    @Override
    public ResponseWrapperAdsDto getAdsMe() {
        return null;
    }

    @Override
    public void updateImageAdDto(Integer id, MultipartFile image) {

    }
}
