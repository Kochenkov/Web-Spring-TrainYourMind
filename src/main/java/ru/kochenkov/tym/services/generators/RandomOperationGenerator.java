package ru.kochenkov.tym.services.generators;

public class RandomOperationGenerator {

    public String createRandomOperation() {
        int amount = (int) (4 * Math.random());
        switch (amount) {
            case 0: return "+";
            case 1: return "-";
            case 2: return "*";
            default: return "/";
        }
    }
}
