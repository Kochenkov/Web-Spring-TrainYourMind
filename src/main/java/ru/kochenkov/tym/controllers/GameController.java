package ru.kochenkov.tym.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kochenkov.tym.models.Equation;
import ru.kochenkov.tym.models.User;
import ru.kochenkov.tym.repositories.EquationRepo;
import ru.kochenkov.tym.services.generators.EquationGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    private EquationGenerator equationGenerator;

    @Autowired
    private EquationRepo equationRepo;

    @GetMapping("/new")
    public String startNewGame(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("userName", user.getUsername());
        ArrayList<String> eqArray = equationGenerator.createRandomEquationArray(2);
        Equation equation = equationGenerator.createEquationFromArray(eqArray);
        equation.setUser(user);
        equationRepo.save(equation);
        model.addAttribute("equation", equation);
        model.addAttribute("showRequestForm", true);
        model.addAttribute("showResponseForm", false);
        return "game_screen.html";
    }

    @PostMapping("/check")
    public String checkAnswer(@AuthenticationPrincipal User user,
                              @RequestParam Long eqId,
                              @RequestParam String userAnswer,
                              Model model) {

        model.addAttribute("userName", user.getUsername());

        Equation equation = equationRepo.findById(eqId);
        try {
            equation.setUserAnswer(Float.parseFloat(userAnswer));
        } catch (NumberFormatException e) {
            equation.setUserAnswer(0.0000001f);
        }

        //setDate
        Date date = new Date();
        String pattern = "d-MM-yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String formattedDate = simpleDateFormat.format(date);
        equation.setDate(formattedDate);
        //

        equationRepo.save(equation);
        model.addAttribute("equation", equation);
        model.addAttribute("showRequestForm", false);
        model.addAttribute("showResponseForm", true);
        return "game_screen.html";
    }
}