package com.management.controller;

import com.management.service.UserService;
import com.management.model.User;
import java.util.List;

public class UserController {
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    public void addUser(String id, String name, String email) {
        service.createUser(id, name, email);
    }

    public List<User> getAllUsers() {
        return service.listUsers();
    }

    public User getUserById(String id) {
        return service.listUsers().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteUser(String id) {
        return service.removeUser(id);
    }

    public boolean updateUser(String originalId, String newId, String name, String email) {
        return service.updateUserDetails(originalId, newId, name, email);
    }
}
