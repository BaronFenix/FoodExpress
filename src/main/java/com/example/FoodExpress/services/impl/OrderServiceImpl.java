package com.example.FoodExpress.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodExpress.helpers.BasketManager;
import com.example.FoodExpress.models.Order;
import com.example.FoodExpress.models.OrderProduct;
import com.example.FoodExpress.models.Product;
import com.example.FoodExpress.models.Status;
import com.example.FoodExpress.models.User;
import com.example.FoodExpress.repositories.OrderProductRepository;
import com.example.FoodExpress.repositories.OrderRepository;
import com.example.FoodExpress.repositories.StatusRepository;
import com.example.FoodExpress.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Override
    public Order addOrder(User user, BasketManager basketManager) {

        Status orderStatus = statusRepository.findByName("Готовится");
        Order order = new Order(LocalDateTime.now(), basketManager.getTotalSum(), orderStatus, user);

        Order savedOrder = orderRepository.save(order);

        List<OrderProduct> products = new ArrayList<>();
        for (Map.Entry<Long, Product> entrySet : basketManager.getBasket().entrySet()) {
            products.add(new OrderProduct(entrySet.getValue().getQuantity(), order, entrySet.getValue()));
        }
        orderProductRepository.saveAll(products);

        return savedOrder;
    }

    @Override
    public Order editOrder(Order order) {
        return editOrder(order);
    }

    @Override
    public void removeOrder(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void removeOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUser(String userLogin) {
        return orderRepository.findAllByUser(userLogin).stream().toList();
    }

    @Override
    public List<Order> getOrdersByStatus(String name) {
        return orderRepository.findAllByStatus(name);
    }

    @Override
    public List<Order> getUserOrdersByStatus(String userLogin, String statusName) {
        return orderRepository.findUserOrdersByStatus(userLogin, statusName);
    }




    @Override
    public Status addStatus(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public Status editStatus(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public void removeStatus(Status status) {
        statusRepository.delete(status);
    }

    @Override
    public void removeStatusById(Long id) {
        statusRepository.deleteById(id);
    }

    @Override
    public Optional<Status> getStatusByName(String name) {
        return Optional.of(statusRepository.findByName(name));
    }

    @Override
    public Optional<Status> getStatusById(Long id) {
        return statusRepository.findById(id);
    }
    
}
