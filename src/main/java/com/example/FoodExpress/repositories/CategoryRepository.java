package com.example.FoodExpress.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.FoodExpress.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query(value = "SELECT * FROM Categories c WHERE c.name = :name", nativeQuery = true)
    public Category findByName(@Param("name") String name);
    
}
