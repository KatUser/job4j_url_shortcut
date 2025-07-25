package ru.job4j.urlshortcut.service.user;

import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    @Transactional
    User save(User user);

    @Transactional
    boolean deleteById(Long userId);

    Optional<User> findById(Long id);

    List<User> getAllUsers();

    Optional<User> findBySite(String site);

}
