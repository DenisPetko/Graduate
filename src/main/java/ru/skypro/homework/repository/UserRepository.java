package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.User;

import javax.persistence.Cacheable;
import javax.transaction.Transactional;
import java.util.Optional;
/**
 * Интерфейс для работы с базой данных пользователей
 */
@Repository
@Cacheable
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String email);
    @Transactional
    @Modifying
    @Query("UPDATE User users set users.password = ?1 where users.id = ?2")
    void setNewPassword(String newPassword, int id);
}
