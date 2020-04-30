package ru.kochenkov.tym.services.generators;

import org.springframework.stereotype.Service;
import ru.kochenkov.tym.models.Equation;

import java.util.ArrayList;

@Service
public class EquationGenerator {

    public ArrayList<String> createRandomEquationArray(int size) {
        ArrayList<String> arr = new ArrayList<>();
        if (size == 0) {
            return new ArrayList<>();
        } else {
            arr.add(Integer.toString(new RandomNumberGenerator().createRandomNumber()));
            for (int i = 0; i < size; i++) {
                arr.add(new RandomOperationGenerator().createRandomOperation());
                arr.add(Integer.toString(new RandomNumberGenerator().createRandomNumber()));
            }
            return arr;
        }
    }

    public Equation createEquationFromArray(ArrayList<String> arr) {
        arr = validateDivisionAndUpdateEquationArray(arr);
        ArrayList<String> newArr = new ArrayList<>(arr);
        Equation equation = new Equation();
        equation.setArray(newArr);
        float firstNum;
        float secondNum;
        float answer = 0;
        for (int i = 0; i < arr.size(); i++) {
            switch (arr.get(i)) {
                case "/": {
                    firstNum = Float.parseFloat(arr.get(i - 1));
                    secondNum = Float.parseFloat(arr.get(i + 1));
                    answer = firstNum / secondNum;
                    arr.remove(i - 1);
                    arr.remove(i - 1);
                    arr.remove(i - 1);
                    arr.add(i - 1, Float.toString(answer));
                    i = 0;
                    break;
                }
                case "*": {
                    firstNum = Float.parseFloat(arr.get(i - 1));
                    secondNum = Float.parseFloat(arr.get(i + 1));
                    answer = firstNum * secondNum;
                    arr.remove(i - 1);
                    arr.remove(i - 1);
                    arr.remove(i - 1);
                    arr.add(i - 1, Float.toString(answer));
                    i = 0;
                    break;
                }
            }
        }
        for (int i = 0; i < arr.size(); i++) {
            switch (arr.get(i)) {
                case "+": {
                    firstNum = Float.parseFloat(arr.get(i - 1));
                    secondNum = Float.parseFloat(arr.get(i + 1));
                    answer = firstNum + secondNum;
                    arr.remove(i - 1);
                    arr.remove(i - 1);
                    arr.remove(i - 1);
                    arr.add(i - 1, Float.toString(answer));
                    i = 0;
                    break;
                }
                case "-": {
                    firstNum = Float.parseFloat(arr.get(i - 1));
                    secondNum = Float.parseFloat(arr.get(i + 1));
                    answer = firstNum - secondNum;
                    arr.remove(i - 1);
                    arr.remove(i - 1);
                    arr.remove(i - 1);
                    arr.add(i - 1, Float.toString(answer));
                    i = 0;
                    break;
                }
            }
        }
        equation.setInitialAnswer(answer);
        return equation;
    }

    private float validateDivision(float a, float b) {
        if (b == 0) {
            b = new RandomNumberGenerator().createRandomNumberWithoutZero();
        }
        while ((a / b) % 0.5 > 0) {
            b = new RandomNumberGenerator().createRandomNumberWithoutZero();
        }
        return b;
    }

    private ArrayList<String> validateDivisionAndUpdateEquationArray(ArrayList<String> arr) {
        float firstNum;
        float secondNum;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i)
                   .equals("/")) {
                firstNum = Float.parseFloat(arr.get(i - 1));
                secondNum = Float.parseFloat(arr.get(i + 1));
                secondNum = validateDivision(firstNum, secondNum);
                if ((secondNum * 10) % 10 == 0) {
                    int intSecondNum = (int) secondNum;
                    arr.set(i + 1, Integer.toString(intSecondNum));
                } else {
                    arr.set(i + 1, Float.toString(secondNum));
                }
            }
        }
        return arr;
    }
}
