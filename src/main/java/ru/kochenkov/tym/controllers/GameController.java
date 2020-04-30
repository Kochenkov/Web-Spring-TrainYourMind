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

import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    EquationGenerator equationGenerator;

    @Autowired
    EquationRepo equationRepo;

    @GetMapping("/new")
    public String startNewGame(@AuthenticationPrincipal User user,
                               Model model, Principal principal) {

        String name = principal.getName();
        model.addAttribute("userName", name);

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
    public String checkAnswer(@RequestParam Long eqId, @RequestParam String userAnswer,
                              Model model, Principal principal) {

        String name = principal.getName();
        model.addAttribute("userName", name);

        Equation equation = equationRepo.findEquationById(eqId);
        try {
            equation.setUserAnswer(Float.parseFloat(userAnswer));
        } catch (NumberFormatException e) {
            equation.setUserAnswer(0.0000001f);
        }
        equationRepo.save(equation);
        model.addAttribute("equation", equation);
        model.addAttribute("showRequestForm", false);
        model.addAttribute("showResponseForm", true);
        return "game_screen.html";
    }
}