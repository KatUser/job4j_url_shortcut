package ru.job4j.urlshortcut.generator;

import org.springframework.stereotype.Service;

import java.util.*;

import static ru.job4j.urlshortcut.utils.Constants.*;
import static ru.job4j.urlshortcut.utils.UtilGenerator.randomChar;
import static ru.job4j.urlshortcut.utils.UtilGenerator.shuffle;

@Service
public class CredentialsGenerator {

    public HashMap<String, String> credentials = new HashMap<>();

    public HashMap<String, String> generate() {
        StringBuilder value = new StringBuilder();
        value.append(randomChar(NUMBERS))
                .append(randomChar(SPECIAL))
                .append(randomChar(LOWERCASE))
                .append(randomChar(UPPERCASE));

        value.append(SYMBOLS.charAt(SECURE_RANDOM.nextInt(SYMBOLS.length())));
        var shuffledValue = shuffle(value.toString());

        StringBuilder key = new StringBuilder();
        key.append(randomChar(NUMBERS))
                .append(randomChar(SPECIAL))
                .append(randomChar(LOWERCASE))
                .append(randomChar(UPPERCASE));
        key.append(SYMBOLS.charAt(SECURE_RANDOM.nextInt(SYMBOLS.length())));
        var shuffledKey = shuffle(key.toString());

        credentials.put(shuffledValue, shuffledKey);

        return credentials;
    }

}
