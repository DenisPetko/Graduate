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
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.validation.ValidationForAds;

import javax.persistence.Cacheable;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {
    private final AdsMapper adsMapper;
    private final AdsRepository adsRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final ValidationForAds validation;

    @Override
    public ResponseWrapperAdsDto getAllAdsDto() {
        Collection<AdsDto> adsAll = adsMapper.mapAdsListToAdsDtoList(adsRepository.findAll());
        return new ResponseWrapperAdsDto(adsAll);
    }

    @Override
    public AdsDto addAds(CreateAdsDto createAdsDto, MultipartFile image) {
        Ads newAds = adsMapper.mapCreatedAdsDtoToAds(createAdsDto);
        newAds.setAuthor(userService.findAuthUser().orElseThrow(UserNotFoundException::new));
        Image newImage = imageService.saveImage(image);
        newAds.setImage(newImage);
        adsRepository.save(newAds);
        return adsMapper.mapAdsToAdsDto(newAds);
    }

    @Override
    public FullAdsDto getFullAds(int id) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        return adsMapper.mapAdsToFullAdsDto(ads);
    }

    @Override
    public boolean removeAdsDto(int id) {
        Optional<User> user = userService.findAuthUser();
        if (validation.validateAds(user.get(), id)) {
                adsRepository.deleteById(id);
                return true;
        }
        return false;
    }

    @Override
    public AdsDto updateAdsDto(int id, CreateAdsDto createAdsDto) {
        if (validation.validateAds(userService.findAuthUser().get(), id)) {
            Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
                ads.setDescription(createAdsDto.getDescription());
                ads.setPrice(createAdsDto.getPrice());
                ads.setTitle(createAdsDto.getTitle());
                return adsMapper.mapAdsToAdsDto(adsRepository.save(ads));
        }
        throw new UserNotFoundException();
    }

    @Override
    public ResponseWrapperAdsDto getAllAdsMe() {
        User user = userService.findAuthUser().orElseThrow(UserNotFoundException::new);
        Collection<Ads> allAds = adsRepository.findAll();
        Collection<Ads> userAds = allAds.stream().filter(x -> x.getAuthor().equals(user)).collect(Collectors.toList());
        return new ResponseWrapperAdsDto(adsMapper.mapAdsListToAdsDtoList(userAds));
    }

    @Override
    public void updateImageAdsDto(int id, MultipartFile image) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        Image updateImage = imageService.updateImage(image, ads.getImage());
        ads.setImage(updateImage);
        adsRepository.save(ads);
    }

}
