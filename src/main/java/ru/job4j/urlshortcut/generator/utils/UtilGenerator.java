package ru.job4j.urlshortcut.generator.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UtilGenerator {

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
