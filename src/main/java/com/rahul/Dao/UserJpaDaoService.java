package com.rahul.Dao;

import com.rahul.Exceptions.UserNotFoundException;
import com.rahul.Repository.UserRepo;
import com.rahul.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserJpaDaoService {

    private final UserRepo userRepo;

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public User findById(int userId){
        return userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException(String.valueOf(userId)));
    }

    public User saveUser(User user){
        return userRepo.save(user);
    }

    public void deleteById(int userId) {
        userRepo.deleteById(userId);
    }
}
