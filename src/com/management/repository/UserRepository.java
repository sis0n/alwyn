package com.management.repository;

import com.management.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(String id) {
        return users.stream().filter(u -> u.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    public boolean deleteUser(String id) {
        return users.removeIf(u -> u.getId().equalsIgnoreCase(id));
    }

    public boolean updateUser(String originalId, User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equalsIgnoreCase(originalId)) {
                users.set(i, updatedUser);
                return true;
            }
        }
        return false;
    }
}
