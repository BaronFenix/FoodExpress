package com.example.FoodExpress.helpers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.example.FoodExpress.models.Product;

@Component
public class BasketManager {
    private Map<Long, Product> basket;
    private double totalSum;

    public BasketManager() {
        basket = new HashMap<>();
        totalSum = 0;
    }
    
    @SuppressWarnings("unchecked")
    public BasketManager(Model model) {
        this.basket = (Map<Long, Product>) model.getAttribute("basket");
        this.totalSum = this.calculateTotalSum();
    }

    public BasketManager(Map<Long, Product> basket) {
        this.basket = basket;
        this.totalSum = this.calculateTotalSum();
    }

    public void addToBasket(Product product, int quantity) {
        long productId = product.getId();
        if(!basket.containsKey(productId)) {
            product.setQuantity(quantity);
            basket.put(productId, product);
            totalSum = calculateTotalSum();
        }
        else{
            setProductQuantity(productId, quantity);
        }
    }

    
    public void setProductQuantity(long productId, int quantity) {
        if(basket.containsKey(productId)) {
            basket.get(productId).setQuantity(quantity);
            totalSum = calculateTotalSum();
        }
    }

    public void remove(long productId) {
        basket.remove(productId);
        totalSum = calculateTotalSum();
    }
    
    public double calculateTotalSum() {
        return basket.entrySet()
            .stream()
            .mapToDouble(x -> x.getValue().getPrice() * x.getValue().getQuantity())
            .sum();
    } 

    public Map<Long,Product> getBasket() {
        return this.basket;
    }

    public void setBasket(Map<Long,Product> basket) {
        this.basket = basket;
    }

    public double getTotalSum() {
        return this.totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }
    
}
