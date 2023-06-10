package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;

/**
 * Interface for CRUD operation with object Image
 * @see ru.skypro.homework.model.Image
 */
public interface ImageService {

    Image saveImage(MultipartFile image);

    Image updateImage(MultipartFile image, Image oldImage);

    byte[] getImage(String id);
}
