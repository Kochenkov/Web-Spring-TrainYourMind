package ru.kochenkov.tym.models;

import java.util.ArrayList;

public class Equation {

    private ArrayList<String> array;
    private float answer;

    public ArrayList<String> getArray() {
        return array;
    }

    public float getAnswer() {
        return answer;
    }

    public void setArray(ArrayList<String> array) {
        this.array = array;
    }

    public void setAnswer(float answer) {
        this.answer = answer;
    }

    public String showEquationBody() {
        String str = "";
        ArrayList<String> arr = this.getArray();
        for (String s : arr) {
            str = str + s + " ";
        }
        return str;
    }
}
