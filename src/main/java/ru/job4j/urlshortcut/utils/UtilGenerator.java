package ru.job4j.urlshortcut.utils;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.job4j.urlshortcut.generator.Constants.SECURE_RANDOM;

@Component
public class UtilGenerator {

    public static char randomChar(String symbols) {
        return symbols.charAt(SECURE_RANDOM.nextInt(symbols.length()));
    }

    public static String shuffle(String str) {
        List<String> chars = Arrays.asList(str.split(""));
        Collections.shuffle(chars);
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            sb.append(chars.get(i));
        }
        return sb.toString();
    }
}
