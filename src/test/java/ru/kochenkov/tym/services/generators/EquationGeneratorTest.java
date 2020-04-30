package ru.kochenkov.tym.services.generators;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.kochenkov.tym.models.Equation;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EquationGeneratorTest {

    private EquationGenerator equationGenerator = new EquationGenerator();

    @Test
    void createEquationArrayWith2Size() {
        ArrayList<String> arr = equationGenerator.createRandomEquationArray(2);
        assertEquals(5, arr.size());
    }

    @Test
    void createEquationArrayWith0Size() {
        ArrayList<String> arr = equationGenerator.createRandomEquationArray(0);
        assertEquals(0, arr.size());
    }

    @Test
    void createEquationArrayWith5Size() {
        ArrayList<String> arr = equationGenerator.createRandomEquationArray(5);
        assertEquals(11, arr.size());
    }

    @Test
    void createEquationFromExactArray() {
        Equation equation = equationGenerator.createEquationFromArray(new ArrayList<String>(Arrays.asList("1", "+", "4", "/", "4")));
        assertEquals(2.0, equation.getInitialAnswer());
        assertEquals(Arrays.asList("1", "+", "4", "/", "4"), equation.getArray());
    }

    @Test
    void createEquationFromExactArrayWithZeroValidation() {
        Equation equation = equationGenerator.createEquationFromArray(new ArrayList<String>(Arrays.asList("8", "+", "3", "/", "4")));
        assertNotNull(equation.getInitialAnswer());
        assertNotEquals(Arrays.asList("8", "+", "3", "/", "4"), equation.getArray());
    }

    @Test
    @Disabled
    void createEquationFromRandomArray() {
        Equation equation = equationGenerator.createEquationFromArray(equationGenerator.createRandomEquationArray(2));
        System.out.println(equation.getArray());
        System.out.println(equation.getInitialAnswer());
    }
}
