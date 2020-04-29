package ru.kochenkov.tym.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;

@Data
@Entity
public class Equation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private ArrayList<String> array;

    private float answer;

    private boolean isCorrect;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Equation() {
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
