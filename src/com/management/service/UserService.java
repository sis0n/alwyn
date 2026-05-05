package com.management.service;

import com.management.model.User;
import com.management.repository.UserRepository;
import java.util.List;

public class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void createUser(String id, String name, String email) {
        User newUser = new User(id, name, email);
        repository.addUser(newUser);
    }

    public List<User> listUsers() {
        return repository.getAllUsers();
    }

    public boolean removeUser(String id) {
        return repository.deleteUser(id);
    }

    public boolean updateUserDetails(String originalId, String newId, String newName, String newEmail) {
        User updatedUser = new User(newId, newName, newEmail);
        return repository.updateUser(originalId, updatedUser);
    }
}
