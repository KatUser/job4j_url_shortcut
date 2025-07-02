package ru.job4j.urlshortcut.controller.statistics;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
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
class StatisticsControllerTest {

    @Autowired
    private StatisticsController statisticsController;

    @Autowired
    private CalledUrlRepository calledUrlRepository;

    @Autowired
    private UserRepository userRepository;

    private final User testUser
            = new User(null, "site", "login", "password", Set.of());

    @BeforeEach
    void setUp() {

        calledUrlRepository.deleteAll();
        userRepository.save(testUser);
    }

    @Test
    void whenThereIsARecordThenReceiveIt() {

        calledUrlRepository.save(new CalledUrl(null, testUser, "test.url", 10L));
        var response = statisticsController
                .getUrlStatistics(new StatisticsRequestDto("test.url")).getBody();
        assertEquals("test.url", response.getUrl());
        assertEquals(10L, (long) response.getTotal());

    }

    @Test
    void whenThereAreNoRecordsThenReceiveZeroCount() {
        var response = statisticsController
                .getUrlStatistics(new StatisticsRequestDto("test.url")).getBody();
        assertEquals("test.url", response.getUrl());
        assertEquals(0L, (long) response.getTotal());
    }


}