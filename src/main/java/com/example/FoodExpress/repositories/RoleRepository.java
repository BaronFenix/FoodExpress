package com.example.FoodExpress.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.FoodExpress.models.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
    
    @Query(value = "select * from Roles u where u.name = :name", nativeQuery = true)
    public Role findRoleByName(@Param("name") String name);
    
}
