package ru.skypro.homework.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.config.WebSecurityConfig;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.ImageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {WebSecurityConfig.class})
class AdsControllerTest {
    @Mock
    private AdsService adsService;
    @Mock
    private CommentService commentService;
    @Mock
    private ImageService imageService;

    @InjectMocks
    AdsController adsController;

    private final CreateAdsDto createAdsDto = new CreateAdsDto();
    private final Ads ads = new Ads();
    private final Comment comment = new Comment();
    private final CommentDto commentDto = new CommentDto();


    @BeforeEach
    public void setUp() {
        createAdsDto.setDescription("test description");
        createAdsDto.setTitle("test title");
        createAdsDto.setPrice(300);

        commentDto.setPk(1);
        commentDto.setText("test commentDto");

        ads.setId(1);
        comment.setId(2);
    }

    @Test
    void shouldReturnAllAds() {
        List<AdsDto> adsDtoList = Collections.singletonList(new AdsDto());
        ResponseWrapperAdsDto adsDto = new ResponseWrapperAdsDto(adsDtoList);

        when(adsService.getAllAdsDto()).thenReturn(adsDto);

        ResponseEntity<ResponseWrapperAdsDto> responseEntity = adsController.getAllAds();

        verify(adsService).getAllAdsDto();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void shouldAddNewAd() {
        AdsDto adsDto = new AdsDto();
        MultipartFile image = new MockMultipartFile("test.jpg", "test.jpg", "image/jpg",
                "test image".getBytes());
        when(adsService.addAds(createAdsDto, image)).thenReturn(adsDto);

        ResponseEntity<AdsDto> response = adsController.addAd(createAdsDto, image);

        verify(adsService).addAds(createAdsDto, image);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(adsDto, response.getBody());
        assertNotNull(response.getBody());
    }

    @Test
    void shouldGetCommentsById() {
        Collection<CommentDto> results = new ArrayList<>();
        ResponseWrapperCommentDto responseWrapperCommentDto = new ResponseWrapperCommentDto(results);

        when(commentService.getComments(ads.getId())).thenReturn(responseWrapperCommentDto);

        ResponseEntity<ResponseWrapperCommentDto> response = adsController.getComments(ads.getId());

        verify(commentService).getComments(ads.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseWrapperCommentDto, response.getBody());
        assertNotNull(response.getBody());
    }

    @Test
    void addComment() {
        String comment = "test";

        when(commentService.addComment(ads.getId(), comment)).thenReturn(commentDto);

        ResponseEntity<CommentDto> response = adsController.addComment(ads.getId(), comment);

        verify(commentService).addComment(ads.getId(), comment);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentDto, response.getBody());
        assertNotNull(response.getBody());
    }

    @Test
    void getAds() {
        FullAdsDto fullAdsDto = new FullAdsDto();

        when(adsService.getFullAds(ads.getId())).thenReturn(fullAdsDto);

        ResponseEntity<FullAdsDto> response = adsController.getAds(ads.getId());

        verify(adsService).getFullAds(ads.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(fullAdsDto, response.getBody());
        assertNotNull(response.getBody());
    }

    @Test
    void removeAds() {
        when(adsService.removeAdsDto(ads.getId())).thenReturn(true);

        ResponseEntity<?> response = adsController.removeAds(ads.getId());

        verify(adsService).removeAdsDto(ads.getId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void shouldUpdateAds() {
        AdsDto adsDto = new AdsDto();
        adsDto.setTitle("test title");
        adsDto.setPrice(300);

        when(adsService.updateAdsDto(ads.getId(), createAdsDto)).thenReturn(adsDto);

        ResponseEntity<AdsDto> response = adsController.updateAds(ads.getId(), createAdsDto);

        verify(adsService).updateAdsDto(ads.getId(), createAdsDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createAdsDto.getTitle(), Objects.requireNonNull(response.getBody()).getTitle());
        assertEquals(createAdsDto.getPrice(), response.getBody().getPrice());
    }

    @Test
    void shouldDeleteCommentWithOK() {
        when(commentService.deleteComment(ads.getId(), comment.getId())).thenReturn(true);

        ResponseEntity<?> response = adsController.deleteComment(ads.getId(), comment.getId());

        verify(commentService).deleteComment(ads.getId(), comment.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void shouldDeleteCommentWithNotFound() {
        when(commentService.deleteComment(ads.getId(), comment.getId())).thenReturn(false);

        ResponseEntity<?> response = adsController.deleteComment(ads.getId(), comment.getId());

        verify(commentService).deleteComment(ads.getId(), comment.getId());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldUpdateComment() {
        when(commentService.updateComment(ads.getId(), comment.getId(), commentDto)).thenReturn(commentDto);

        ResponseEntity<CommentDto> response = adsController.updateComment(ads.getId(), comment.getId(), commentDto);

        verify(commentService).updateComment(ads.getId(), comment.getId(), commentDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentDto.getText(), Objects.requireNonNull(response.getBody()).getText());
        assertEquals(commentDto.getPk(), response.getBody().getPk());
    }

    @Test
    void getAdsMe() {
        List<AdsDto> adsDtoList = Collections.singletonList(new AdsDto());
        ResponseWrapperAdsDto adsDto = new ResponseWrapperAdsDto(adsDtoList);

        when(adsService.getAllAdsMe()).thenReturn(adsDto);

        ResponseEntity<ResponseWrapperAdsDto> response = adsController.getAdsMe();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void updateImage() {
        MultipartFile image = new MockMultipartFile("image.jpg", new byte[]{1, 2, 3});

        doNothing().when(adsService).updateImageAdsDto(ads.getId(), image);

        ResponseEntity<byte[]> response = adsController.updateImage(ads.getId(), image);

        verify(adsService).updateImageAdsDto(ads.getId(), image);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getImage() {
        String id = "1";
        byte[] mockImage = {1, 2, 3};

        when(imageService.getImage(id)).thenReturn(mockImage);

        ResponseEntity<byte[]> response = adsController.getImage(id);

        verify(imageService).getImage(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertArrayEquals(mockImage, response.getBody());
    }
}