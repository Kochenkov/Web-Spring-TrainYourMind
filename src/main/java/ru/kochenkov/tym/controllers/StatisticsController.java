package ru.kochenkov.tym.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kochenkov.tym.models.Equation;
import ru.kochenkov.tym.models.User;
import ru.kochenkov.tym.repositories.EquationRepo;
import ru.kochenkov.tym.repositories.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    private final String infoMessage = "Вы не решили ни одной задачи, у вас пока нет статистики ¯\\_(ツ)_/¯";

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EquationRepo equationRepo;

    @GetMapping
    public String openStatisticsScreen(@AuthenticationPrincipal User user,
                                       Model model,
                                       @RequestParam(defaultValue = "10") String size) {

        model.addAttribute("userName", user.getUsername());
        int arraySize = Integer.parseInt(size);
        model.addAttribute("size", arraySize + 10);
        model.addAttribute("loadMoreFields", true);
        List<Equation> equations = equationRepo.findByUser(user);
        List<Equation> sortedEquations = new ArrayList<>();
        for (Equation eq : equations) {
            if (eq.isAnswered()) {
                sortedEquations.add(0, eq);
            }
        }
        if (sortedEquations.size() > 0) {
            if (sortedEquations.size() > arraySize) {
                sortedEquations = sortedEquations.subList(0, arraySize);
            } else {
                model.addAttribute("loadMoreFields", false);
            }
            model.addAttribute("userEquations", sortedEquations);
        } else {
            model.addAttribute("message", infoMessage);
        }
        return "statistics_screen.html";
    }
}