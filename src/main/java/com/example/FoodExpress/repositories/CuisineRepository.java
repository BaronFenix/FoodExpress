package com.example.FoodExpress.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.FoodExpress.models.Cuisine;

public interface CuisineRepository extends CrudRepository<Cuisine, Long> {
    
    @Query("SELECT c FROM Cuisines c WHERE c.name = :name")
    public Optional<Cuisine> findByName(@Param("name") String name);

}
