package com.example.FoodExpress.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.FoodExpress.helpers.BasketManager;
import com.example.FoodExpress.models.Category;
import com.example.FoodExpress.models.Product;
import com.example.FoodExpress.models.Restaurant;
import com.example.FoodExpress.services.impl.ProductServiceImpl;
import com.example.FoodExpress.services.impl.RestaurantServiceImpl;


@Controller
@SessionAttributes({"authUser", "basket"})
public class RestaurantController {

    @Autowired
    RestaurantServiceImpl restaurantService;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    BasketManager basketManager;
    
    @GetMapping("/restaurant/{restaurantName}")
    public String restaurantView(@PathVariable(value = "restaurantName") String restaurantName, Model model) {

        Optional<Restaurant> restaurant = restaurantService.getRestaurantByName(restaurantName);
        if(!restaurant.isPresent()) {
            return "redirect:/";
        }
        else {
            model.addAttribute("restaurant", restaurant.get());
        }

        List<Product> productsList = productService.getProductsByRestaurant(restaurantName);
        List<Category> categoriesList = productService.getCategoriesByRestaurant(restaurantName);
        Map<String, List<Product>> productsByCategory = getProductsMapByCategory(productsList);
        
        model.addAttribute("productsMapByCategory", productsByCategory);
        model.addAttribute("categoriesList", categoriesList);
        model.addAttribute("basket", model.getAttribute("basket"));

        return "restaurant";
    }


    @PostMapping("/restaurant/{restaurantName}")
    public String addToBasket(
        @PathVariable(value = "restaurantName") String restaurantName,
        @RequestParam Long selectedProductId,
        @RequestParam int productQuantity,
        Model model)
        {
            Product product = productService.getProductById(selectedProductId).get();
            basketManager.addToBasket(product, productQuantity);
            
            model.addAttribute("basket", basketManager.getBasket());

        return restaurantView(restaurantName, model);
    }

    
    private Map<String, List<Product>> getProductsMapByCategory(List<Product> products) {
        Map<String, List<Product>> productsByCategory = new HashMap<>();

        for(Product product : products) {
            String categoryName = product.getCategory().getName();
            if(!productsByCategory.containsKey(categoryName)) {
                productsByCategory.put(categoryName, new ArrayList<>(Arrays.asList(product)));
            }
            else {
                productsByCategory.get(categoryName).add(product);
            }
        }
        return productsByCategory;
    }

}
