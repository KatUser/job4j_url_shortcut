package ru.job4j.urlshortcut.repository.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ru.job4j.urlshortcut.model.User;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(null, "testsite.com", "login", "password", Set.of());
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void whenSaveUser_thenCanGetUserById() {
        assertTrue(userRepository.existsById(user.getId()));
        assertTrue(userRepository.findUserById(user.getId()).isPresent());
    }

    @Test
    public void whenSaveUser_thenCanGetUserBySite() {
        assertTrue(userRepository.existsBySite(user.getSite()));
    }

    @Test
    public void whenSaveUser_thenCanGetUserByLogin() {

        assertTrue(userRepository.findUserByLogin(user.getLogin()).isPresent());
    }

    @Test
    public void whenDeleteUser_thenCannotGetUser() {
        userRepository.deleteById(2L);
        assertFalse(userRepository.existsById(2L));
    }

    @Test
    public void whenUserDoesntExist_thenCannotGetUser() {
        assertFalse(userRepository.existsById(3L));
    }

    @Test
    public void whenUserDoesNotExist_thenCannotGetUserByLogin() {
        assertThat(userRepository.findUserByLogin("TestLogin")).isEmpty();
    }

    @Test
    public void whenUserDoesNotExist_thenCanGetUserByLogin() {
        assertTrue(userRepository.findUserByLogin("randomLogin").isEmpty());
    }

}