package com.example.FoodExpress.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodExpress.models.Category;
import com.example.FoodExpress.models.Product;
import com.example.FoodExpress.repositories.CategoryRepository;
import com.example.FoodExpress.repositories.ProductRepository;
import com.example.FoodExpress.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService  {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product editProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void removeProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void removeProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return Optional.of(productRepository.findByName(name));
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByRestaurant(String name) {
        return productRepository.findByRestaurantName(name);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category editCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void removeCategory(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public void removeCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return Optional.of(categoryRepository.findByName(name));
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getCategoriesByRestaurant(String name) {
        return categoryRepository.findCategoriesByRestaurant(name);
    }
    
}
