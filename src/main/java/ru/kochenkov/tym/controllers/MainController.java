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
}