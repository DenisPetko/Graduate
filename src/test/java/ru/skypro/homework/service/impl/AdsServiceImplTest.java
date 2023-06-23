package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdsServiceImplTest {
    @Mock
    private AdsMapper adsMapper;
    @Mock
    private AdsRepository adsRepository;
    @Mock
    private UserService userService;
    @Mock
    private ImageService imageService;
    @InjectMocks
    private AdsServiceImpl adsService;


    private final Ads testAds = new Ads();
    private final CreateAdsDto createAdsDto = new CreateAdsDto();
    private final User testUser = new User();

    @BeforeEach
    public void setUp() {

        testAds.setId(11);
        testAds.setDescription("ads description");
        testAds.setTitle("ads title");
        testAds.setPrice(300);

        createAdsDto.setDescription("adsDto Description");
        createAdsDto.setTitle("adsDto Title");
        createAdsDto.setPrice(303);
    }

    @Test
    @DisplayName("Получить список всех объявлений getAllAdsDto()")
    public void getAllAdsDtoTest() {
        List<Ads> adList = Collections.singletonList(new Ads());
        List<AdsDto> adsDtoList = Collections.singletonList(new AdsDto());

        when(adsRepository.findAll()).thenReturn(adList);
        when(adsMapper.mapAdsListToAdsDtoList(adList)).thenReturn(adsDtoList);

        ResponseWrapperAdsDto result = adsService.getAllAdsDto();

        assertNotNull(result);
        assertEquals(adsDtoList, result.getResults());

        verify(adsRepository, times(1)).findAll();
        verify(adsMapper, times(1)).mapAdsListToAdsDtoList(adList);
    }

    @Test
    @DisplayName("Создать объявления addAds(CreateAdsDto createAdsDto, MultipartFile image)")
    public void addAdsTest() {
        CreateAdsDto adDto = new CreateAdsDto();
        adDto.setDescription("test description");
        adDto.setTitle("test title");
        adDto.setPrice(50);
        testAds.setAuthor(testUser);

        when(userService.findAuthUser()).thenReturn(Optional.of(testUser));
        when(adsMapper.mapCreatedAdsDtoToAds(adDto)).thenReturn(testAds);
        when(imageService.saveImage(any(MultipartFile.class))).thenReturn(new Image());
        when(adsRepository.save(any(Ads.class))).thenReturn(new Ads());
        when(adsMapper.mapAdsToAdsDto(any(Ads.class))).thenReturn(new AdsDto());

        AdsDto result = adsService.addAds(adDto, new MockMultipartFile("test", new byte[11]));

        assertNotNull(result);

        verify(adsRepository, times(1)).save(any(Ads.class));
        verify(adsMapper, times(1)).mapCreatedAdsDtoToAds(adDto);
        verify(adsMapper, times(1)).mapAdsToAdsDto(any(Ads.class));
    }

    @Test
    @DisplayName("Получить все данные из объявления getFullAds(int id)")
    public void getFullAdsTest() {
        FullAdsDto fullAdsDto = new FullAdsDto();

        when(adsRepository.findById(testAds.getId())).thenReturn(Optional.of(testAds));
        when(adsMapper.mapAdsToFullAdsDto(testAds)).thenReturn(fullAdsDto);

        FullAdsDto result = adsService.getFullAds(testAds.getId());

        assertEquals(fullAdsDto, result);

        verify(adsRepository, times(1)).findById(testAds.getId());
        verify(adsMapper, times(1)).mapAdsToFullAdsDto(testAds);
    }

    @Test
    @DisplayName("Получить список объявлений пользователя getAllAdsMe()")
    public void getAllAdsMeTest() {
        testAds.setAuthor(testUser);
        List<Ads> adsList = Collections.singletonList(testAds);
        List<AdsDto> adsDtoList = Collections.singletonList(new AdsDto());

        when(userService.findAuthUser()).thenReturn(Optional.of(testUser));
        when(adsRepository.findAll()).thenReturn(adsList);
        when(adsMapper.mapAdsListToAdsDtoList(adsList)).thenReturn(adsDtoList);

        ResponseWrapperAdsDto result = adsService.getAllAdsMe();

        assertNotNull(result);
        assertEquals(adsDtoList, result.getResults());

        verify(userService, times(1)).findAuthUser();
        verify(adsRepository, times(1)).findAll();
        verify(adsMapper, times(1)).mapAdsListToAdsDtoList(adsList);
    }

    @Test
    @DisplayName("Тест возвращения списка объявлений, когда их нет")
    public void getAllAdsMeHasNoAdsTest() {
        when(userService.findAuthUser()).thenReturn(Optional.of(testUser));
        when(adsRepository.findAll()).thenReturn(Collections.emptyList());
        when(adsMapper.mapAdsListToAdsDtoList(Collections.emptyList())).thenReturn(Collections.emptyList());

        ResponseWrapperAdsDto result = adsService.getAllAdsMe();

        assertNotNull(result);
        assertEquals(Collections.emptyList(), result.getResults());

        verify(userService, times(1)).findAuthUser();
        verify(adsRepository, times(1)).findAll();
        verify(adsMapper, times(1)).mapAdsListToAdsDtoList(Collections.emptyList());
    }

    @Test
    @DisplayName("Обновление фотографии объявления, когда оно существует")
    public void updateImageAdDtoFoundTest() {
        testAds.setAuthor(testUser);
        testAds.setImage(new Image("imageOld", new byte[20]));
        MockMultipartFile image = new MockMultipartFile("image", new byte[30]);

        when(adsRepository.findById(testAds.getId())).thenReturn(Optional.of(testAds));
        when(imageService.updateImage(image, testAds.getImage())).thenReturn(new Image());

        adsService.updateImageAdsDto(testAds.getId(), image);

        verify(adsRepository, times(1)).findById(testAds.getId());
        verify(adsRepository, times(1)).save(testAds);
    }

    @Test
    @DisplayName("Обновление фотографии объявления, когда оно не существует")
    public void updateImageAdDtoNotFoundTest() {
        when(adsRepository.findById(testAds.getId())).thenReturn(Optional.empty());

        assertThrows(AdsNotFoundException.class,
                () -> adsService.updateImageAdsDto(testAds.getId(),
                        new MockMultipartFile("image", new byte[30])));

        verify(adsRepository, times(1)).findById(testAds.getId());
    }

}