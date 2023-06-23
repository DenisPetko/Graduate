package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;

    @Override
    public Image saveImage(MultipartFile image) {
        Image newImage = new Image();
        try{
            byte[] bytes = image.getBytes();
            newImage.setImage(bytes);
        } catch (IOException e){
            e.printStackTrace();
        }
        newImage.setId(UUID.randomUUID().toString());
        return repository.saveAndFlush(newImage);
    }

    @Override
    public Image updateImage(MultipartFile newImage, Image oldImage) {
        try {
            byte[] bytes = newImage.getBytes();
            oldImage.setImage(bytes);
        }catch (IOException e){
            e.printStackTrace();
        }
        return repository.saveAndFlush(oldImage);
    }

    @Override
    public byte[] getImage(String id) {
        Image image = repository.findById(id).orElseThrow(ImageNotFoundException::new);
        return image.getImage();
    }
}
