package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Comment;
/**
 * Интерфейс для работы с базой данных комментариев
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
