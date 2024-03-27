package com.example.FoodExpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.FoodExpress.models.User;
import com.example.FoodExpress.services.impl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("authUser")
public class AuthController {
    
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("loginErrorMsg", "");

        return "login";
    }

    @PostMapping("/login")
    public String checkLogin(@ModelAttribute("user") User user, Model model, HttpSession httpSession) {
        User validatedUser = userService.validateUser(user);
        if(validatedUser != null) {
            httpSession.setAttribute("authUser", validatedUser);
            httpSession.setMaxInactiveInterval(900);
            return "redirect:/";
        }
        else {
            model.addAttribute("loginErrorMsg", "Не верный логин или пароль");
        }
        return "/login";
    }

    @GetMapping("/register")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("userForm", user);

        return "register";
    }

    @PostMapping("/register")
    public String addUser (@ModelAttribute("userForm") User userForm, Model model) { 
        if(userForm != null) {
            userService.addUser(userForm);
            return "redirect:/login";
        }

        return "/";
    }
}
