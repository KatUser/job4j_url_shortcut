package ru.job4j.urlshortcut.siteutils;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class SiteConverterTest {

    @Autowired
    private SiteConverter siteConverter;

    @Test
    void whenEncryptAndDecryptThenReceiveSame() throws Exception {
        var testUrl = "https://www.test.com";
        var encryptedUrl = siteConverter.encrypt(testUrl);
        assertEquals(testUrl, siteConverter.decrypt(encryptedUrl));

    }

}