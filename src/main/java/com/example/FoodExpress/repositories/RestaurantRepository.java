package com.example.FoodExpress.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.FoodExpress.models.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    @Query(value = "SELECT * FROM Restaurants r WHERE r.name = :name", nativeQuery = true)
    public Restaurant findByName(@Param("name") String name);

    @Query(value = "SELECT * FROM Restaurants r " +
    "JOIN restaurant_cuisines ON r.id = restautant_id " +
    "JOIN Cuisines c ON c.id = cuisine_id " +
    "WHERE c.name = :name ",
     nativeQuery = true)
    public List<Restaurant> findAllByCuisine(@Param("name") String name);
}
