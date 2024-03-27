package com.example.FoodExpress.controllers;

import com.example.FoodExpress.helpers.BasketManager;
import com.example.FoodExpress.models.Order;
import com.example.FoodExpress.models.User;
import com.example.FoodExpress.services.impl.OrderServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@SessionAttributes({"authUser", "basket"})
public class OrderController {
    
    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    BasketManager basketManager;

    @GetMapping("/order/create")
    public String createOrderView(Model model) {
        if(model.getAttribute("authUser") == null) {
            return "redirect:/";
        }

        model.addAttribute("user", model.getAttribute("authUser"));        
        model.addAttribute("total_sum", basketManager.getTotalSum());

        return "/order";
    }

    @PostMapping("/order/create")
    public String createOrder(Model model) {
        User user = (User) model.getAttribute("authUser");
        if(user == null) {
            return "redirect:/login";
        }
        orderService.addOrder((User) model.getAttribute("authUser"), basketManager);

        return "redirect:/user/" + user.getLogin() + "/orders";
    }

    @GetMapping("/user/{username}/orders")
    public String userOrdersView(Model model) {
        User user = (User) model.getAttribute("authUser");
        if(user == null) {
            return "redirect:/login";
        }

        List<Order> userOrders = orderService.getOrdersByUser(user.getLogin());
        
        model.addAttribute("authUser", user);
        model.addAttribute("orders", userOrders);

        return "history";
    }

    @GetMapping("/user/{username}/order/{orderId}/details")
    public String orderDetailsView(@PathVariable("orderId") long orderId, Model model) {
        User user = (User) model.getAttribute("authUser");
        if(user == null) {
            return "redirect:/login";
        }

        List<Order> userOrders = orderService.getOrdersByUser(user.getLogin());
        model.addAttribute("orders", userOrders);
        model.addAttribute("orderId", orderId);
        model.addAttribute("authUser", user);

        return "order-details";
    }
    

}
