package com.example.FoodExpress.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.FoodExpress.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
    @Query("select u from Users u where u.login = :login")
    public Optional<User> findByLogin(@Param("login") String login);

    @Query("SELECT u FROM Users u JOIN Role r ON r.id = role_id WHERE r.name = :roleName")
    public Iterable<User> findByRole(@Param("roleName") String roleName);

}
