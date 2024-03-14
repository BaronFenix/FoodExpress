package com.example.FoodExpress.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.FoodExpress.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
    @Query(value = "select * from Users u where u.login = :login", nativeQuery = true)
    public User findByLogin(@Param("login") String login);

    @Query(value = "SELECT * FROM Users u JOIN Role r ON r.id = role_id WHERE r.name = :roleName", nativeQuery = true)
    public List<User> findByRole(@Param("roleName") String roleName);

}
