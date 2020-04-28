package ru.kochenkov.tym.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kochenkov.tym.repositories.UserRepo;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String showMainScreen(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("userName", name);
        return "main_screen.html";
    }

    @GetMapping("/login")
    public String showLoginScreen() {
        return "login_screen.html";
    }

    @GetMapping("/info")
    public String showInfoScreen(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("userName", name);
        return "info_screen.html";
    }

    @GetMapping("/play")
    public String showGameScreen(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("userName", name);
        model.addAttribute("equation", "test 123");
        return "game_screen.html";
    }
}