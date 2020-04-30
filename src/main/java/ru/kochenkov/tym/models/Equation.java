package ru.kochenkov.tym.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;

@Entity
public class Equation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private ArrayList<String> array;

    private float initialAnswer;

    private float userAnswer;

    private boolean answered;

    private boolean correct;

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

    public Long getId() {
        return id;
    }

    public ArrayList<String> getArray() {
        return array;
    }

    public float getInitialAnswer() {
        return initialAnswer;
    }

    public float getUserAnswer() {
        return userAnswer;
    }

    public boolean isAnswered() {
        return answered;
    }

    public boolean isCorrect() {
        return correct;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArray(ArrayList<String> array) {
        this.array = array;
    }

    public void setInitialAnswer(float initialAnswer) {
        this.initialAnswer = initialAnswer;
    }

    public void setUserAnswer(float userAnswer) {
        this.userAnswer = userAnswer;
        this.answered = true;
        this.correct = (userAnswer == initialAnswer);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
