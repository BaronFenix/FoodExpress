package com.example.FoodExpress.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.FoodExpress.models.Restaurant;
import com.example.FoodExpress.services.impl.RestaurantServiceImpl;


@Controller
@SessionAttributes("authUser")
public class RestaurantController {

    @Autowired
    RestaurantServiceImpl restaurantService;

    
    @GetMapping("/restaurant/{name}")
    public String restaurantView(@PathVariable(value = "name") String name, Model model) {
        // if(!restaurantService.existsByName(name)) {
        //     return "redirect:/not_found";
        // }

        Optional<Restaurant> restaurant = restaurantService.getRestaurantByName(name);
        if(restaurant.isPresent()) {
            model.addAttribute("restaurant", restaurant.get());
        }

        return "restaurant";
    }
    

}
