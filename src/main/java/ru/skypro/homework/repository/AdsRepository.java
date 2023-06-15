package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ads;
/**
 * Интерфейс для работы с базой данных объявлений
 */
@Repository
public interface AdsRepository extends JpaRepository<Ads, Integer> {

}
