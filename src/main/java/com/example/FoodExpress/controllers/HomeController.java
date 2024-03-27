package com.example.FoodExpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.FoodExpress.models.Restaurant;
import com.example.FoodExpress.services.impl.RestaurantServiceImpl;

import jakarta.servlet.http.HttpSession;


@Controller
@SessionAttributes({"authUser", "basket"})
public class HomeController {
    
    @Autowired
    private RestaurantServiceImpl restaurantService;

    @GetMapping("/")
    public String main(Model model, HttpSession httpSession) {
        Iterable<Restaurant> restaurantsList = restaurantService.getAllRestaurants();

        model.addAttribute("authUser", httpSession.getAttribute("authUser"));
        model.addAttribute("restaurantsList", restaurantsList);
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession httpSession) {
        return main(model, httpSession);
    }

}
