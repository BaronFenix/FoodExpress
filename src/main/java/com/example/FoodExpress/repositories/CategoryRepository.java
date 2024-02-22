package com.example.FoodExpress.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.FoodExpress.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("SELECT c FROM Categories c WHERE c.name = :name")
    public Optional<Category> findByName(@Param("name") String name);
    
}
