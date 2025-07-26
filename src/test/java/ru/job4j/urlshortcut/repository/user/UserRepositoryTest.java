package ru.job4j.urlshortcut.repository.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.job4j.urlshortcut.model.Role;
import ru.job4j.urlshortcut.model.User;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    @Mock
    private Role roleMock = Mockito.mock(Role.class);

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void whenSaveUser_thenCanGetUser() {
        User user1 = new User(1L, "testsite.com", "login", "password", Set.of(roleMock));
        userRepository.save(user1);
        assertTrue(userRepository.existsById(user1.getId()));
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

}