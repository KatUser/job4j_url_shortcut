package ru.job4j.urlshortcut.generator;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static ru.job4j.urlshortcut.generator.utils.Constants.SYMBOLS;
import static ru.job4j.urlshortcut.generator.utils.UtilGenerator.shuffle;

@Service
public class CredentialsGenerator {

    public HashMap<String, String> credentials = new HashMap<>();

    public HashMap<String, String> generate() {

        String value = new Random().ints(8, 0, SYMBOLS.length())
                .mapToObj(SYMBOLS::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());

        var shuffledValue = shuffle(value);

        String key = new Random().ints(8, 0, SYMBOLS.length())
                .mapToObj(SYMBOLS::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());

        var shuffledKey = shuffle(key);

        credentials.put(shuffledValue, shuffledKey);

        return credentials;
    }

}
