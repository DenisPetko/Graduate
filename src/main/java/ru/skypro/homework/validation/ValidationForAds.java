package ru.skypro.homework.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.exception.UserValidationException;
import ru.skypro.homework.model.Role;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;

@RequiredArgsConstructor
@Component
public class ValidationForAds {
    private final AdsRepository adsRepository;
    public boolean validateAds(User user, int id) {
        if (user.getUsername()
                    .equals(adsRepository.getReferenceById(id).getAuthor().getUsername())
                    || user.getRole().equals(Role.ADMIN)) {
                    return true;
        } else {
            throw new UserValidationException();
        }
    }
}
