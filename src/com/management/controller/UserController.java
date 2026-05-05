package com.management.controller;

import com.management.service.UserService;
import com.management.model.User;
import java.util.List;

public class UserController {
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    public boolean addUser(String id, String name, String email) {
        return service.createUser(id, name, email);
    }

    public List<User> getAllUsers() {
        return service.listUsers();
    }

    public User getUserById(String id) {
        return service.getUserById(id);
    }

    public boolean deleteUser(String id) {
        return service.removeUser(id);
    }

    public boolean updateUser(String originalId, String newId, String name, String email) {
        return service.updateUserDetails(originalId, newId, name, email);
    }
}
