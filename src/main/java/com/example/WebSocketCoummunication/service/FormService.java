package com.example.WebSocketCoummunication.service;

import com.example.WebSocketCoummunication.model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FormService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public FormService(UserRepository userRepository, UserInfoRepository userInfoRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
    }

    public boolean isUserValid(String username, String email) {
        Optional<UserInfo> user = userInfoRepository.findByNickAndEmail(username, email);

        return user.isPresent();
    }

    private boolean hasAllFieldsPresent(UserWithInfo user) {
        return user.user().isPresent() || user.userInfo().isPresent();
    }

    @Transactional
    public Optional<UserWithInfo> addUser(UserInfo userInfo) {
        if (isUserValid(userInfo.getNick(), userInfo.getEmail())) return Optional.empty();

        var user = new User(userInfo.getNick(), userInfo.getPassword(), LocalDateTime.now());

        try {

            userInfoRepository.save(userInfo);
            userRepository.save(user);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Cannot save this user. Error: " + e.getMessage());
        }

        UserWithInfo userWithInfo = new UserWithInfo(
            Optional.of(user),
            Optional.of(userInfo)
        );

        if (!hasAllFieldsPresent(userWithInfo)) {
            return Optional.empty();
        }

        return Optional.of(userWithInfo);
    }

    public boolean isUsernameAndEmailValid(String username, String password) {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);

        return user.isPresent();
    }

}
