package com.miniorm.app;

import com.miniorm.models.User;
import com.miniorm.repository.InMemoryRepository;
import com.miniorm.service.UserService;

public class Main {
    public static void main(String[] args) {
        InMemoryRepository<User, Long> repo = new InMemoryRepository<>();
        UserService service = new UserService(repo);


        User u1 = new User(1, "juan", "juan@example.com");
        User u2 = new User(2, "ana", "ana@example.com");

        service.createUser(u1);
        service.createUser(u2);

        System.out.println("Usuarios registrados:");
        service.getAllUsers().forEach(user ->
                System.out.println(user.getName() + " - " + user.getEmail())
        );
    }
}


