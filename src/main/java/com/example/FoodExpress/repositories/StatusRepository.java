package com.example.FoodExpress.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.FoodExpress.models.Status;

public interface StatusRepository extends CrudRepository<Status, Long>  {

    @Query(value = "select * from Statuses s where s.name = :name", nativeQuery = true)
    public Status findByName(@Param("name") String name);

}
