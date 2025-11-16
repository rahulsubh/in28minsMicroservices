package com.rahul.Dao;

import com.rahul.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static AtomicInteger userIdCounter = new AtomicInteger(0);

    static {
        users.add(User.builder().id(userIdCounter.incrementAndGet()).name("Rahul").birthDate(LocalDate.now().minusYears(30)).build());
        users.add(User.builder().id(userIdCounter.incrementAndGet()).name("John").birthDate(LocalDate.now().minusYears(25)).build());
        users.add(User.builder().id(userIdCounter.incrementAndGet()).name("Alice").birthDate(LocalDate.now().minusYears(28)).build());
    }

    public List<User> findAll(){
        return users;
    }

    public User findById(int userId){
        return users
                .stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .orElse(null);
    }

    public User saveUser(User user){
        user.setId(userIdCounter.incrementAndGet());
        users.add(user);
        return user;
    }

    public void deleteById(int userId) {
        users.removeIf(user -> user.getId() == userId);
    }
}
