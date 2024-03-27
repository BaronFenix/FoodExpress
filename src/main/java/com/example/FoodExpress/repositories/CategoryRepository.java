package com.example.FoodExpress.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.FoodExpress.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query(value = "SELECT * FROM Categories c WHERE c.name = :name", nativeQuery = true)
    public Category findByName(@Param("name") String name);
    
    @Query(value = "SELECT DISTINCT c.id, c.name "+ 
                    "FROM categories c "+
                    "JOIN products p ON p.category_id = c.id " +
                    "JOIN restaurants r ON p.restaurant_id = r.id " +
                    "WHERE r.name = :name", nativeQuery = true)
    public List<Category> findCategoriesByRestaurant(@Param("name") String name);
}
