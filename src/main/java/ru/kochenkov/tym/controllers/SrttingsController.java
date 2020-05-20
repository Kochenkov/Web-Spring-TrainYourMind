package ru.kochenkov.tym.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kochenkov.tym.models.User;

@Controller
@RequestMapping("/settings")
public class SrttingsController {

    @GetMapping
    public String openSettingsScreen(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("userName", user.getUsername());
        return "settings_screen.html";
    }
}