package ru.kochenkov.tym.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String showMainScreen(Model model) {
        model.addAttribute("title", "Главная");
        return "main_screen.html";
    }
}
