package com.sda.crisangabi.bookstore.controller;

import com.sda.crisangabi.bookstore.controller.dto.UserRegistrationDto;
import com.sda.crisangabi.bookstore.model.Role;
import com.sda.crisangabi.bookstore.model.User;
import com.sda.crisangabi.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")//url-ul este luat de la th:action din html-ul corespunzator
    public String register(@Valid @ModelAttribute(name = "user") UserRegistrationDto userRegistrationDto, BindingResult result) {
        if (result.hasErrors()) {
            return "/register";
        }
        User user = userService.findByEmail(userRegistrationDto.getEmail());
        if (user != null) {
            result.rejectValue("Email", null, "Email exists!");
            return "/register";
        }
        userService.saveUser(userRegistrationDto.getEmail(), userRegistrationDto.getPassword(), Role.CUSTOMER);
        return "redirect:/registration?success";
        //"?success" deoarece avem o conditie in html care verifica daca operataia a fost efectuat acu succes
    }

    @PostMapping("/create-admin")//url-ul este luat de la th:action din html-ul corespunzator
    public String createAdmin(@Valid @ModelAttribute(name = "user") UserRegistrationDto userRegistrationDto, BindingResult result) {
        if (result.hasErrors()) {
            return "create-admin";//numele fisierului html
        }
        User user = userService.findByEmail(userRegistrationDto.getEmail());
        if (user != null) {
            result.rejectValue("Email", null, "Email exists!");
            return "create-admin";
        }
        userService.saveUser(userRegistrationDto.getEmail(), userRegistrationDto.getPassword(), Role.ADMIN);
        return "redirect:/home-admin"; //redirect nu incarca pagina
        //la redirect trebuie sa pun "/"
    }
}
