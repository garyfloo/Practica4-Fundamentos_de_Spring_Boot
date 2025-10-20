package com.miniorm.service;

import com.miniorm.models.User;
import com.miniorm.repository.Repository;
import java.util.*;

public class UserService {
    private final Repository<User, Long> repository;

    public UserService(Repository<User, Long> repository) {
        this.repository = repository;
    }

    public User createUser(User user) {
        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
