package ru.kochenkov.tym.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kochenkov.tym.models.Role;
import ru.kochenkov.tym.models.User;
import ru.kochenkov.tym.repositories.UserRepo;

import java.util.Collections;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    private final String infoMessage = "Такой пользователь уже зарегистрирован!";

    @GetMapping
    public String showRegistrationScreen() {
        return "registration_screen.html";
    }

    @PostMapping
    public String addNewUserInDb(@RequestParam String username,
                                 @RequestParam String password,
                                 Model model) {
        try {
            User userFromDb = userRepo.findByUsername(username);
            if (userFromDb != null) {
                model.addAttribute("message", infoMessage);
                return "registration_screen.html";
            }
        } catch (Exception ex) {
            model.addAttribute("message", infoMessage);
            return "registration_screen.html";
        }
        User user = new User(username, password);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}