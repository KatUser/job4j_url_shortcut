package ru.job4j.urlshortcut.siteutils;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SiteExtractor {

    public static String extractSite(String siteUrl) {

        String siteExtracted = siteUrl;

        if (siteUrl.startsWith("https://")) {
            siteExtracted = siteUrl.replace("https://", "");
            return siteExtracted.toLowerCase();
        }

        if (siteUrl.startsWith("http://")) {
            siteExtracted = siteUrl.replace("http://", "");
            return siteExtracted.toLowerCase();
        }

        return siteExtracted.toLowerCase();
    }
}
