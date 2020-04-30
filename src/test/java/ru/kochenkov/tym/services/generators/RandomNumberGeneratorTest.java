package ru.kochenkov.tym.services.generators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomNumberGeneratorTest {

    private RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    @Test
    void createRandomNumber() {
        int i = 100;
        while (i > 0) {
            int num = randomNumberGenerator.createRandomNumber();
            assertTrue((num - 9) <= 0);
            assertTrue((num + 1) > 0);
            assertTrue((num + 1) <= 10);
            i--;
        }
    }

    @Test
    void createRandomNumberWithoutZero() {
        int i = 100;
        while (i > 0) {
            int num = randomNumberGenerator.createRandomNumberWithoutZero();
            assertTrue(num > 0);
            assertTrue(num <= 9);
            i--;
        }
    }
}