package ru.job4j.urlshortcut.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.model.User;
import ru.job4j.urlshortcut.repository.user.UserRepository;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceDB implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean deleteById(Long userId) {
        return userRepository.delete(userId) > 0L;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findBySite(String site) {
        return userRepository.findUserBySite(site);
    }

}
