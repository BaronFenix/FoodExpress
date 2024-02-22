package com.example.FoodExpress.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.FoodExpress.models.Role;
import com.example.FoodExpress.models.User;
import com.example.FoodExpress.repositories.RoleRepository;
import com.example.FoodExpress.repositories.UserRepository;
import com.example.FoodExpress.services.UserService;

public class UserServiceImpl implements UserService {
    
    @Autowired  
    private UserRepository userRepository;

    @Autowired  
    private RoleRepository roleRepository;
    
    
    @Override
    public User addUser(User user) {
        User savedUser = userRepository.save(user);

        return savedUser;
    }

    @Override
    public User editUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void removeUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Iterable<User> getUsersByRole(String roleName) {
        return userRepository.findByRole(roleName);
    }

    // role actions
    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role editRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void removeRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void removeRoleById(Long id) {
        roleRepository.deleteById(id);
    }

}
