package com.example.FoodExpress.services;

import java.util.List;
import java.util.Optional;

import com.example.FoodExpress.models.Cuisine;
import com.example.FoodExpress.models.Restaurant;

public interface RestaurantService {
    
    public Restaurant addRestaurant(Restaurant restaurant);

    public void removeRestaurant(Restaurant restaurant);

    public void removeRestaurantById(Long id);

    public Restaurant editRestaurant(Restaurant restaurant);

    public Optional<Restaurant> getRestaurantByName(String name);

    public Optional<Restaurant> getRestaurantById(Long id);

    public Iterable<Restaurant> getAllRestaurants();

    public List<Restaurant> getRestaurantsByCuisine(String name);



    public Cuisine addCuisine(Cuisine cuisine);

    public void removeCuisine(Cuisine cuisine);

    public void removeCuisineById(Long id);

    public Cuisine editCuisine(Cuisine cuisine);

    public Optional<Cuisine> getCuisineByName(String name);

    public Optional<Cuisine> getCuisineById(Long id);

    public Iterable<Cuisine> getAllCuisines();


    



}
