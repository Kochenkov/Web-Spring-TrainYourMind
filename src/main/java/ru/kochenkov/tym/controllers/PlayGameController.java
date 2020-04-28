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

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/play")
public class PlayGameController {

    @GetMapping
    public String startNewGame(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("userName", name);
        model.addAttribute("equation", "test 123");
        //генерим пример с ответом и айдишником
        //отправляем пример на фронт

        return "game_screen.html";
    }

    @PostMapping
    public String checkAnswer(Model model, Principal principal) {
        //ждем запрос с ответом и айдишником
        //проверяем правильность ответа по айдишнику
        //показываем результат
        return "game_screen.html";
    }
}