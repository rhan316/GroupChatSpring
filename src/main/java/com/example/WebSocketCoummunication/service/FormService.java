package com.example.WebSocketCoummunication.service;

import com.example.WebSocketCoummunication.model.User;
import com.example.WebSocketCoummunication.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class FormService {

    private final UserRepository userRepository;

    @Autowired
    public FormService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isValidUser(String userName, String password) {
        Optional<User> user = userRepository.findByUsernameAndPassword(userName, password);

        return user.isPresent();
    }

    public boolean isUsernameTaken(String userName) {
        Optional<User> user = userRepository.findByUsername(userName);

        return user.isPresent();
    }

    public void addNewUser(String nickname, String password) {
        User user = new User(nickname, password, LocalDateTime.now());
        userRepository.save(user);
    }
}
