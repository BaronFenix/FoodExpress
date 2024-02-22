package com.example.FoodExpress.services;

import java.util.Optional;

import com.example.FoodExpress.models.Category;
import com.example.FoodExpress.models.Product;

public interface ProductService {
    
    public Product addProduct(Product product); 

    public Product editProduct(Product product);

    public void removeProduct(Product product);

    public void removeProductById(Long id);

    public Optional<Product> getProductByName(String name);

    public Optional<Product> getProductById(Long id);

    public Iterable<Product> getAllProducts();


    public Category addCategory(Category category);
    
    public Category editCategory(Category category);

    public void removeCategory(Category category);

    public void removeCategoryById(Long id);

    public Optional<Category> getCategoryByName(String name);

    public Optional<Category> getCategoryById(Long id);

}