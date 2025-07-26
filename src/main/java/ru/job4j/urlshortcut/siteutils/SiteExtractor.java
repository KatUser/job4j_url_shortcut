package ru.job4j.urlshortcut.siteutils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class SiteExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteExtractor.class);

    public static String extractSite(String siteUrl) {

        String siteExtracted = siteUrl;

        if (siteUrl.startsWith("https://")) {
            siteExtracted = siteUrl.replace("https://", "").toLowerCase();
            LOGGER.debug("It starts with https{}", siteExtracted);
            return siteExtracted;
        }

        if (siteUrl.startsWith("http://")) {
            siteExtracted = siteUrl.replace("http://", "").toLowerCase();
            LOGGER.debug("It starts with http{}", siteExtracted);
            return siteExtracted;
        }

        return siteExtracted;
    }
}
