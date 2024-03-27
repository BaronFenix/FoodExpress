package com.example.FoodExpress.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.FoodExpress.models.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query(value = "SELECT * FROM Orders o " +
    "JOIN orders_products ON o.id = order_id " +
    "JOIN Stauses s ON s.id = status_id " +
    "WHERE s.name = :name",
     nativeQuery = true)
    public List<Order> findAllByStatus(@Param("name") String name);

    @Query(value = "SELECT o.id, o.order_date, o.total_sum, o.status_id, o.user_id " + 
    "FROM Orders o " +
    "JOIN orders_products op ON o.id = op.order_id " +
    "JOIN Users u ON u.id = o.user_id " +
    "WHERE u.login = :login",
     nativeQuery = true)
    public Set<Order> findAllByUser(@Param("login") String userLogin);

    @Query(value = "SELECT * FROM Orders o " +
    "JOIN orders_products ON o.id = order_id " +
    "JOIN Stauses s ON s.id = status_id " +
    "JOIN Users u ON u.id = status_id " +
    "WHERE u.login = :login and s.name = :status",
     nativeQuery = true)
    public List<Order> findUserOrdersByStatus(@Param("login") String userLogin, @Param("status") String statusName);
}
