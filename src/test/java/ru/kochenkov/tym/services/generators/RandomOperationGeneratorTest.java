package ru.kochenkov.tym.services.generators;

import org.junit.jupiter.api.Test;
import ru.kochenkov.tym.services.generators.RandomOperationGenerator;

import static org.junit.jupiter.api.Assertions.*;

class RandomOperationGeneratorTest {

    private RandomOperationGenerator randomOperationGenerator = new RandomOperationGenerator();

    @Test
    void createRandomOperation() {
        int plus_count = 0;
        int minus_count = 0;
        int multiply_count = 0;
        int division_count = 0;
        int i = 100;
        while (i > 0) {
            String op = randomOperationGenerator.createRandomOperation();
            switch (op) {
                case "+":
                    plus_count++; break;
                case "-":
                    minus_count++; break;
                case "*":
                    multiply_count++; break;
                case "/":
                    division_count++;
            }
            i--;
        }
        int common_count = plus_count + minus_count + multiply_count + division_count;
        assertTrue(common_count == 100);
        assertTrue(plus_count > 0);
        assertTrue(minus_count > 0);
        assertTrue(multiply_count > 0);
        assertTrue(division_count > 0);
    }
}