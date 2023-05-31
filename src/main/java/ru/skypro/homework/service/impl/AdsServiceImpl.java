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
import ru.skypro.homework.model.Role;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.UserService;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public boolean removeAdDto(long id) {
        if (checkAccess(id)) {
            adsRepository.deleteById(id);
            return true;
        }
        throw new UserNotFoundException();
    }

    @Override
    public AdsDto updateAdDto(long id, CreateAdsDto createAdsDto) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        if (checkAccess(id)) {
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
    public void updateImageAdDto(long id, MultipartFile image) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        ads.setImage("pictures/NewCottage.jpeg");
    }

    @Override
    public boolean checkAccess(long id) {
        Role role = Role.ADMIN;
        Ads ad = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        Optional<User> user = userService.findAuthUser();
        if (user.isPresent()) {
            User notOptionalUser = user.get();
            String currentPrincipalName = notOptionalUser.getUsername();
            return ad.getAuthor().getUsername().equals(currentPrincipalName)
                    || notOptionalUser.getAuthorities().contains(role);
        }
        return false;
    }
}
