package com.example.WebSocketCoummunication.service;

import com.example.WebSocketCoummunication.model.User;
import com.example.WebSocketCoummunication.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

}
