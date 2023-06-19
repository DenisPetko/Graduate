package ru.skypro.homework.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.exception.UserValidationException;
import ru.skypro.homework.model.Role;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.CommentRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ValidationForComments {
    private final CommentRepository commentRepository;
    public boolean validateComments(User user, int id) {
        if (user.getUsername()
                    .equals(commentRepository.getReferenceById(id)
                            .getAuthor().getUsername()) // поскольку поле username у нас Unique, то удобнее всего проверять именно эот параметр
                            || user.getRole() == Role.ADMIN) {
                            return true;
        } else {
            throw new UserValidationException();
        }
    }
}
