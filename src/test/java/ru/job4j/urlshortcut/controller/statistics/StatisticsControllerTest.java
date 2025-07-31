package ru.job4j.urlshortcut.controller.statistics;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.job4j.urlshortcut.dto.statistics.StatisticsRequestDto;

import ru.job4j.urlshortcut.model.CalledUrl;
import ru.job4j.urlshortcut.model.User;
import ru.job4j.urlshortcut.repository.calledurl.CalledUrlRepository;
import ru.job4j.urlshortcut.repository.user.UserRepository;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
class StatisticsControllerTest {

    @Autowired
    private StatisticsController statisticsController;

    @Autowired
    private CalledUrlRepository calledUrlRepository;

    @Autowired
    private UserRepository userRepository;


    private User testUser = new User(1L, "site", "login", "password", Set.of());

    @BeforeEach
    void setUp() {

        calledUrlRepository.deleteAll();
    }

    @Test
    void whenThereIsARecordThenReceiveIt() {
        userRepository.save(testUser);
        calledUrlRepository.save(new CalledUrl(1L, testUser, "test.url", 10L));
        var response = statisticsController
                .getUrlStatistics(new StatisticsRequestDto("test.url")).getBody();
        assertTrue(response.getUrl().equals("test.url"));
        assertTrue(response.getTotal().equals(10L));

    }

    @Test
    void whenThereAreNoRecordsThenReceiveZeroCount() {
        var response = statisticsController
                .getUrlStatistics(new StatisticsRequestDto("test.url")).getBody();
        assertTrue(response.getUrl().equals("test.url"));
        assertTrue(response.getTotal().equals(0L));
    }



}