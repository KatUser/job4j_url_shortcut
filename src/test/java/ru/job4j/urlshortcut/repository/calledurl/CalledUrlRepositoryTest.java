package ru.job4j.urlshortcut.repository.calledurl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.urlshortcut.model.CalledUrl;
import ru.job4j.urlshortcut.model.User;
import ru.job4j.urlshortcut.repository.user.UserRepository;

@Disabled
@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class CalledUrlRepositoryTest {

    @Autowired
    private CalledUrlRepository calledUrlRepository;

    private User user = new User();

    @Autowired
    private UserRepository userRepository;

    private CalledUrl calledUrl = new CalledUrl(user, "test.url");

    @BeforeEach
    void setUp() {
        calledUrlRepository.deleteAll();
    }
}