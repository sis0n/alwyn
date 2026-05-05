package com.management.service;

import com.management.model.User;
import com.management.repository.UserRepository;
import java.util.List;

public class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean createUser(String id, String name, String email) {
        if (repository.getUserById(id) != null) {
            return false;
        }
        User newUser = new User(id, name, email);
        repository.addUser(newUser);
        return true;
    }

    public List<User> listUsers() {
        return repository.getAllUsers();
    }

    public User getUserById(String id) {
        return repository.getUserById(id);
    }

    public boolean removeUser(String id) {
        return repository.deleteUser(id);
    }

    public boolean updateUserDetails(String originalId, String newId, String newName, String newEmail) {
        User updatedUser = new User(newId, newName, newEmail);
        return repository.updateUser(originalId, updatedUser);
    }
}
