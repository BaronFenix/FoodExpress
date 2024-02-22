package com.example.FoodExpress.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.FoodExpress.models.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurants r WHERE r.name = :name")
    public Optional<Restaurant> findByName(@Param("name") String name);

    @Query("SELECT r FROM Restaurants r" +
    "JOIN restaurant_cuisines ON r.id = restautant_id" +
    "JOIN Cuisines c ON c.id = cuisine_id" +
    "WHERE c.name = :name")
    public Iterable<Restaurant> findAllByCuisine(@Param("name") String name);
}
