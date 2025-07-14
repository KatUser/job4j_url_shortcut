package ru.job4j.urlshortcut.controller.siteutils;

public class SiteExtractor {

    public static String extractSite(String siteUrl) {

        String siteExtracted = siteUrl;

        if (siteUrl.startsWith("https://")) {
            System.out.println("It starts with https");
            siteExtracted = siteUrl.replace("https://", "").toLowerCase();
            return siteExtracted;
        }

        if (siteUrl.startsWith("http://")) {
            System.out.println("It starts with http");
            siteExtracted = siteUrl.replace("http://", "").toLowerCase();
            System.out.println(siteExtracted);
            return siteExtracted;
        }

        return siteExtracted;
    }
}
