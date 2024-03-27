package com.example.FoodExpress.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.FoodExpress.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = "SELECT * FROM Products p WHERE p.name = :name", nativeQuery = true)
    public Product findByName(@Param("name") String name);

    // 
    @Query(value = "SELECT p.id, p.name, p.price, p.description, p.img_path, p.category_id, p.restaurant_id "+
                    "FROM Products p "+
                    "JOIN Restaurants r ON r.id = restaurant_id " +
                    "WHERE r.name = :name", nativeQuery = true)
    public List<Product> findByRestaurantName(@Param("name") String name);
    
}
