package ru.job4j.urlshortcut.converter;

import org.springframework.stereotype.Service;

import static ru.job4j.urlshortcut.utils.Constants.*;
import static ru.job4j.urlshortcut.utils.UtilGenerator.randomChar;
import static ru.job4j.urlshortcut.utils.UtilGenerator.shuffle;

@Service
public class SiteConverter {

    public String generateCode() {
        StringBuilder value = new StringBuilder();
        value.append(randomChar(NUMBERS))
                .append(randomChar(SPECIAL))
                .append(randomChar(LOWERCASE))
                .append(randomChar(UPPERCASE));

        value.append(SYMBOLS.charAt(SECURE_RANDOM.nextInt(SYMBOLS.length())));

        return shuffle(value.toString());
    }

}
