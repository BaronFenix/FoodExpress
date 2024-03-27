package com.example.FoodExpress.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.FoodExpress.models.OrderProduct;

public interface OrderProductRepository extends CrudRepository<OrderProduct, Long>{
    
}
