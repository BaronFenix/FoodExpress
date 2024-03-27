package com.example.FoodExpress.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodExpress.models.Role;
import com.example.FoodExpress.models.User;
import com.example.FoodExpress.repositories.RoleRepository;
import com.example.FoodExpress.repositories.UserRepository;
import com.example.FoodExpress.services.UserService;

@Service
public class UserServiceImpl implements UserService  {
    
    @Autowired  
    private UserRepository userRepository;

    @Autowired  
    private RoleRepository roleRepository;
    
    
    @Override
    public User addUser(User user) {
        User userFromDB = userRepository.findByLogin(user.getLogin());
        if (userFromDB != null) {
            return null;
        }
        Role UserRole = roleRepository.findRoleByName("ROLE_USER");
        user.setRole(UserRole);

        return userRepository.save(user);
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
        return Optional.of(userRepository.findByLogin(login));
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
    public List<User> getUsersByRole(String roleName) {
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

    @Override
    public Optional<Role> findRoleByName(String name) {
        return Optional.of(roleRepository.findRoleByName(name));
    }

    public User validateUser(User user) {
        User userFromDb = userRepository.findByLogin(user.getLogin());
        if (user.getLogin().equals(userFromDb.getLogin()) && user.getPassword().equals(userFromDb.getPassword())) {
            return userFromDb;
        }
        return null;
    }

}
