package com.example.FoodExpress.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.FoodExpress.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = "SELECT * FROM Products p WHERE p.name = :name", nativeQuery = true)
    public Product findByName(@Param("name") String name);
    
}
