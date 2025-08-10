package ru.job4j.urlshortcut.repository.calledurl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.urlshortcut.model.CalledUrl;
import ru.job4j.urlshortcut.model.User;
import ru.job4j.urlshortcut.repository.user.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class CalledUrlRepositoryTest {

    @Autowired
    private CalledUrlRepository calledUrlRepository;

    @Autowired
    private UserRepository userRepository;

    private final User testUser = new User();

    private final CalledUrl testCalledUrl = new CalledUrl(testUser, "test.url");

    @BeforeEach
    void setUp() {
        userRepository.save(testUser);
        calledUrlRepository.save(testCalledUrl);
    }

    @AfterEach
    void tearDown() {
        calledUrlRepository.deleteAll();
        userRepository.deleteAll();

    }

    @Test
    void whenCreateUrlThenReceiveItWithZeroCount() {
        var savedUrl = calledUrlRepository.findByUrl(testCalledUrl.getUrl());
        assertEquals(1, savedUrl.get().getCount());
        assertEquals(1, calledUrlRepository.count());
    }

    @Test
    void whenSaveAnotherCalledUrlThenPreviousDoesntChange() {
        calledUrlRepository.save(new CalledUrl(testUser, "supertestUrl.com"));
        var savedUrlFirst = calledUrlRepository.findByUrl(testCalledUrl.getUrl());
        assertEquals(1, savedUrlFirst.get().getCount());
    }

    @Test
    void whenUpdateThenGetSingleRow() {
        calledUrlRepository.updateByUrl("test.url");
        assertEquals(1, calledUrlRepository.count());
    }

    @Test
    void whenCalledUrlExists() {
        assertTrue(calledUrlRepository.existsByUrl(testCalledUrl.getUrl()));
    }

    @Test
    void whenCalledDoesNotExists() {
        assertFalse(calledUrlRepository.existsByUrl("somefake.url"));
    }

    @Test
    void whenCanFindCalledUrlByUrl() {
        assertEquals(testCalledUrl, calledUrlRepository.findByUrl(testCalledUrl.getUrl()).get());
    }

    @Test
    void whenCannotFindCalledUrlByUrl() {
        assertTrue(calledUrlRepository.findByUrl("somefake.url").isEmpty());
    }

}