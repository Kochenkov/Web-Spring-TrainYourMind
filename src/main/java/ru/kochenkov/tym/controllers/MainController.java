package ru.kochenkov.tym.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kochenkov.tym.models.Equation;
import ru.kochenkov.tym.models.User;
import ru.kochenkov.tym.repositories.EquationRepo;
import ru.kochenkov.tym.repositories.UserRepo;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EquationRepo equationRepo;

    @GetMapping
    public String openMainScreen(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("userName", name);
        return "main_screen.html";
    }

    @GetMapping("/login")
    public String openLoginScreen() {
        return "login_screen.html";
    }

    @GetMapping("/info")
    public String openInfoScreen(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("userName", name);
        return "info_screen.html";
    }

    @GetMapping("/statistics")
    public String openStatisticsScreen(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("userName", name);
        User user = userRepo.findByUsername(name);
        List<Equation> equations = equationRepo.findByUser(user);
        List<Equation> sortedEquations = new ArrayList<>();
        for (Equation eq : equations) {
            if (eq.isAnswered()) {
                sortedEquations.add(eq);
            }
        }
        model.addAttribute("userEquations", sortedEquations);
        return "statistics_screen.html";
    }
}