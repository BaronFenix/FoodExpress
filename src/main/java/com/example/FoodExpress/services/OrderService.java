package com.example.FoodExpress.services;

import java.util.List;
import java.util.Optional;

import com.example.FoodExpress.helpers.BasketManager;
import com.example.FoodExpress.models.Order;
import com.example.FoodExpress.models.Status;
import com.example.FoodExpress.models.User;

public interface OrderService {
    
    public Order addOrder(User user, BasketManager basketManager);

    public Order editOrder(Order order);

    public void removeOrder(Order order);

    public void removeOrderById(Long id);

    public Optional<Order> getOrderById(Long id);

    public Iterable<Order> getAllOrders();

    public List<Order> getOrdersByUser(String userLogin);

    public List<Order> getOrdersByStatus(String name);

    public List<Order> getUserOrdersByStatus(String userLogin, String statusName);


    public Status addStatus(Status status);
    
    public Status editStatus(Status status);

    public void removeStatus(Status status);

    public void removeStatusById(Long id);

    public Optional<Status> getStatusByName(String name);

    public Optional<Status> getStatusById(Long id);

}
