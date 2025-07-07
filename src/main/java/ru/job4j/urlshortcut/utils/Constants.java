package ru.job4j.urlshortcut.utils;

import java.security.SecureRandom;

public class Constants {

    public static final String NUMBERS = "1234567890";

    public static final String SPECIAL = "!@$%&*?";

    public static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";

    public static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String SYMBOLS = NUMBERS + SPECIAL + LOWERCASE + UPPERCASE;

//    public static final String SYMBOLS = NUMBERS + LOWERCASE + UPPERCASE;

    public static final SecureRandom SECURE_RANDOM = new SecureRandom();
}
