package com.example.FoodExpress.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodExpress.models.Cuisine;
import com.example.FoodExpress.models.Restaurant;
import com.example.FoodExpress.repositories.CuisineRepository;
import com.example.FoodExpress.repositories.RestaurantRepository;
import com.example.FoodExpress.services.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    
    @Autowired
    private RestaurantRepository restaurantRepository; 

    @Autowired
    private CuisineRepository cuisineRepository;


    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void removeRestaurant(Restaurant restaurant) {
       restaurantRepository.delete(restaurant);
    }

    @Override
    public void removeRestaurantById(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant editRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Optional<Restaurant> getRestaurantByName(String name) {
        return Optional.of(restaurantRepository.findByName(name));
    }

    @Override
    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Iterable<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Iterable<Restaurant> getRestaurantsByCuisine(String name) {
        return restaurantRepository.findAllByCuisine(name);
    }

    @Override
    public Cuisine addCuisine(Cuisine cuisine) {
        return cuisineRepository.save(cuisine);
    }

    @Override
    public void removeCuisine(Cuisine cuisine) {
        cuisineRepository.delete(cuisine);
    }

    @Override
    public void removeCuisineById(Long id) {
        cuisineRepository.deleteById(id);
    }

    @Override
    public Cuisine editCuisine(Cuisine cuisine) {
       return cuisineRepository.save(null);
    }

    @Override
    public Optional<Cuisine> getCuisineByName(String name) {
        return Optional.of(cuisineRepository.findByName(name));
    }

    @Override
    public Optional<Cuisine> getCuisineById(Long id) {
        return cuisineRepository.findById(id);
    }

    @Override
    public Iterable<Cuisine> getAllCuisines() {
        return cuisineRepository.findAll();
    }
    
}
