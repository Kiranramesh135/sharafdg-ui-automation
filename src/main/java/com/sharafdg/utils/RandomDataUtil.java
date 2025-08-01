package com.sharafdg.utils;

import java.util.Random;
import java.util.UUID;

public class RandomDataUtil {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final Random RANDOM = new Random();
    private static final String[] EMAIL_DOMAINS = {
            "gmail.com", "yahoo.com", "outlook.com", "hotmail.com",
            "techhub.dev", "myemail.xyz", "inboxmail.net", "userbase.io",
            "webuser.tech", "protonmail.com", "dubaimail.ae", "sharafmail.com"
    };

    public static String getRandomFirstName() {
        return capitalize(getRandomString(8));
    }

    public static String getRandomLastName() {
        return capitalize(getRandomString(8));
    }

    public static String getRandomEmail() {
        String localPart = "user_" + UUID.randomUUID().toString().substring(0, 8);
        String domain = EMAIL_DOMAINS[RANDOM.nextInt(EMAIL_DOMAINS.length)];
        return localPart + "@" + domain;
    }

    public static String getRandomPassword(int length) {
        return getRandomAlphaNumericString(length);
    }

    public static String getRandomPhoneNumber() {
        return "5" + getRandomDigits(8);
    }

    private static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    private static String getRandomAlphaNumericString(int length) {
        String alphanum = CHARACTERS + CHARACTERS.toUpperCase() + DIGITS;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(alphanum.charAt(RANDOM.nextInt(alphanum.length())));
        }
        return sb.toString();
    }

    private static String getRandomDigits(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        }
        return sb.toString();
    }

    private static String capitalize(String str) {
        if (str == null || str.length() == 0) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
