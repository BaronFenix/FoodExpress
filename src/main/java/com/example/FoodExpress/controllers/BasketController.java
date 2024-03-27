package com.example.FoodExpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.FoodExpress.helpers.BasketManager;
import com.example.FoodExpress.services.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes({"authUser", "basket"})
public class BasketController {
    
    @Autowired
    ProductService productService;

    @Autowired
    BasketManager basketManager;

    @GetMapping("/basket")
    public String basketView(Model model) {
        model.addAttribute("basket", basketManager.getBasket());
        model.addAttribute("total_sum", basketManager.getTotalSum());

        return "basket";
    }

    @PostMapping("/basket")
    public String changeQuantity(Model model,
        @RequestParam Long selectedProductId,
        @RequestParam int productQuantity)
        {
            basketManager.setProductQuantity(selectedProductId, productQuantity);
            model.addAttribute("basket", basketManager.getBasket());

        return basketView(model);
    }

    @DeleteMapping("/basket")
    public String basketRemove(Model model, @RequestParam Long selectedProductId, HttpSession session) {
        
        basketManager.remove(selectedProductId);
        session.setAttribute("basket", basketManager.getBasket());

        return basketView(model);
    }

}
