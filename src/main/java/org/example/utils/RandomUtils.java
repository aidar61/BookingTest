package org.example.utils;

import com.github.javafaker.Faker;

public class RandomUtils {
    private static final Faker faker = new Faker();

    public static String generateName() {
        return "AUTOTEST " + faker.name().firstName();
    }

    public static String generateLastName() {
        return "AUTOTEST " + faker.name().lastName();
    }

    public static int generatePrice() {
        return faker.number().numberBetween(1000, 2000);
    }

    public static String generateAdditionalNeeds() {
        return faker.chuckNorris().fact();
    }
}
