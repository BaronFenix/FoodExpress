package com.example.FoodExpress.services;

import java.util.Optional;

import com.example.FoodExpress.models.Role;
import com.example.FoodExpress.models.User;

public interface UserService {

    public User addUser(User user);

    public void removeUser(User user);

    public void removeUserById(Long id);

    public Optional<User> getUserByLogin(String login);

    public Optional<User> getUserById(Long id);

    public User editUser(User user);

    public Iterable<User> getAllUsers();

    public Iterable<User> getUsersByRole(String roleName);
    

    
    public Role addRole(Role role);

    public Role editRole(Role role);

    public void removeRole(Role role);

    public void removeRoleById(Long id);
    
}
