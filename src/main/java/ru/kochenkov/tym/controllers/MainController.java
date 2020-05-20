package ru.kochenkov.tym.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kochenkov.tym.models.Equation;
import ru.kochenkov.tym.models.User;
import ru.kochenkov.tym.repositories.EquationRepo;
import ru.kochenkov.tym.repositories.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private final String infoMessage = "Вы не решили ни одной задачи, у вас пока нет статистики ¯\\_(ツ)_/¯";

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EquationRepo equationRepo;

    @GetMapping
    public String openMainScreen(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("userName", user.getUsername());
        return "main_screen.html";
    }

    @GetMapping("/login")
    public String openLoginScreen() {
        return "login_screen.html";
    }

    @GetMapping("/settings")
    public String openInfoScreen(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("userName", user.getUsername());
        return "settings_screen.html";
    }

    @GetMapping("/statistics")
    public String openStatisticsScreen(@AuthenticationPrincipal User user,
                                       Model model,
                                       @RequestParam(defaultValue = "10") String size) {
        int arraySize = Integer.parseInt(size);
        model.addAttribute("size", arraySize + 10);
        model.addAttribute("userName", user.getUsername());
        List<Equation> equations = equationRepo.findByUser(user);
        List<Equation> sortedEquations = new ArrayList<>();
        for (Equation eq : equations) {
            if (eq.isAnswered()) {
                sortedEquations.add(0, eq);
            }
        }
        if (sortedEquations.size()>0) {
            if (sortedEquations.size()>arraySize) {
                sortedEquations = sortedEquations.subList(0, arraySize);
            }
            model.addAttribute("userEquations", sortedEquations);
        } else {
            model.addAttribute("message", infoMessage);
        }
        return "statistics_screen.html";
    }
}