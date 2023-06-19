package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Image;

import javax.persistence.Cacheable;

@Cacheable
public interface ImageRepository extends JpaRepository<Image, String> {
}
