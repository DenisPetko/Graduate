package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ads;

import javax.persistence.Cacheable;

/**
 * Интерфейс для работы с базой данных объявлений
 */
@Repository
@Cacheable
public interface AdsRepository extends JpaRepository<Ads, Integer> {

}
