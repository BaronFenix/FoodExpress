package com.example.FoodExpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.FoodExpress.models.User;
import com.example.FoodExpress.services.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@SessionAttributes("authUser")
public class UserController {
    
    @Autowired
    UserServiceImpl userService;

    @GetMapping("user/profile/{username}")
    public String getMethodName(@PathVariable("username") String username, Model model) {
        
        model.addAttribute("authUser");
        
        
        return "profile";
    }


    @PostMapping("user/profile/{username}")
    public String editUser(@PathVariable("username") String username, @ModelAttribute("authUser") User modifiedUser, Model model) {
        userService.editUser(modifiedUser);
        
        return "profile";
    }
    
    

}
