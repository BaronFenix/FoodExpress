package com.example.FoodExpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.FoodExpress.models.User;
import com.example.FoodExpress.services.impl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
@SessionAttributes({"authUser", "basket"})
public class UserController {
    
    @Autowired
    UserServiceImpl userService;

    @GetMapping("user/{username}/profile")
    public String getMethodName(@PathVariable("username") String username, Model model, HttpSession session) {

        model.addAttribute("authUser", session.getAttribute("authUser"));
        
        return "profile";
    }


    @PostMapping("user/{username}/profile")
    public String editUser(@PathVariable("username") String username, @ModelAttribute("authUser") User modifiedUser, Model model) {
        userService.editUser(modifiedUser);
        
        return "profile";
    }

    @GetMapping("/user/sign-out")
    public String signOutView(Model model) {
        return "sign-out";
    }

    @PostMapping("/user/sign-out")
    public String signOut(Model model, HttpSession session, SessionStatus sessionStatus) {
        session.invalidate();
        sessionStatus.setComplete();

        return "redirect:/";
    }
    

}
