package com.example.FoodExpress.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.FoodExpress.models.Restaurant;
import com.example.FoodExpress.models.User;
import com.example.FoodExpress.services.impl.RestaurantServiceImpl;
import com.example.FoodExpress.services.impl.UserServiceImpl;




@Controller
@SessionAttributes("authUser")
public class HomeController {
    
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RestaurantServiceImpl restaurantService;

    @ModelAttribute("authUser")
    public User user() {
        return new User();
    }

    @GetMapping("/")
    public String main(Model model) {
        Iterable<Restaurant> restaurantsList = restaurantService.getAllRestaurants();
        
        model.addAttribute("authUser");
        model.addAttribute("restaurantsList", restaurantsList);
        return "home";
    }


    @GetMapping("/home")
    public String home(Model model) {

        return "home";
    }


    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("loginErrorMsg", "");

        return "login";
    }

    @PostMapping("/login")
    public String checkLogin(@ModelAttribute("user") User user, Model model) {
        Optional<User> userFromDb = userService.getUserByLogin(user.getLogin());

        if(userFromDb.isPresent()) {
            if (user.getLogin().equals(userFromDb.get().getLogin()) && user.getPassword().equals(userFromDb.get().getPassword())) {
                model.addAttribute("authUser", userFromDb.get());

                return "redirect:/";
            }
        }
        model.addAttribute("loginErrorMsg", "Не верный логин или пароль");

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
            return "redirect:/register";
        }

        return "/";
    }


    @GetMapping("/sign-out")
    public String signOutView(Model model) {
        return "sign-out";
    }

    @PostMapping("/sign-out")
    public String signOut(Model model, SessionStatus sessionStatus) {
        sessionStatus.setComplete();

        return "redirect:/";
    }
    

    @GetMapping("/basket")
    public String basketView(Model model) {

        return "basket";
    }

}
