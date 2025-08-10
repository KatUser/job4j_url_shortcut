package ru.job4j.urlshortcut.siteutils;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class SiteExtractorTest {

    @Test
    void whenExtractSiteWithHttpThenGetSite() {
        var testSite = "http://testSite.com";
        assertEquals("testsite.com", SiteExtractor.extractSite(testSite));

    }

    @Test
    void whenExtractSiteWithHttpsThenGetSite() {
        var testSite = "https://testSite.com";
        assertEquals("testsite.com", SiteExtractor.extractSite(testSite));

    }

    @Test
    void whenExtractSiteWithNoHttpThenGetSite() {
        var testSite = "www.testSite.com";
        assertEquals("www.testsite.com", SiteExtractor.extractSite(testSite));

    }

}