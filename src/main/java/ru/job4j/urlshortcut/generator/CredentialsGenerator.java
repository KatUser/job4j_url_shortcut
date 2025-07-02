package ru.job4j.urlshortcut.generator;

import org.springframework.stereotype.Service;

import java.util.*;

import static ru.job4j.urlshortcut.generator.Constants.*;
import static ru.job4j.urlshortcut.utils.UtilGenerator.randomChar;
import static ru.job4j.urlshortcut.utils.UtilGenerator.shuffle;

@Service
public class CredentialsGenerator implements Generator {

    public HashMap<String, String> credentials = new HashMap<>();

    private int passwordLength = 10;

    @Override
    public HashMap<String, String> generate() {
        StringBuilder value = new StringBuilder(passwordLength);
        value.append(randomChar(NUMBERS))
                .append(randomChar(SPECIAL))
                .append(randomChar(LOWERCASE))
                .append(randomChar(UPPERCASE));

        value.append(SYMBOLS.charAt(SECURE_RANDOM.nextInt(SYMBOLS.length())));
        var shuffledValue = shuffle(value.toString());

        StringBuilder key = new StringBuilder(passwordLength);
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
