package com.example.WebSocketCoummunication.service;

import com.example.WebSocketCoummunication.model.User;
import com.example.WebSocketCoummunication.model.UserInfo;
import com.example.WebSocketCoummunication.model.UserInfoRepository;
import com.example.WebSocketCoummunication.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean isValidUser(String userName, String password) {
        Optional<User> user = userRepository.findByUsernameAndPassword(userName, password);

        return user.isPresent();
    }

    public boolean isUsernameTaken(String userName) {
        Optional<User> user = userRepository.findByUsername(userName);

        return user.isPresent();
    }

    public boolean isUserInfoTaken(String userName) {
        Optional<UserInfo> userInfo = userInfoRepository.findByNick(userName);

        return userInfo.isPresent();
    }

    public Optional<UserInfo> saveUserDetails(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
        return Optional.of(userInfo);
    }

    public Optional<User> addNewUser(String nickname, String password) {
        if (isUsernameTaken(nickname)) {
            return Optional.empty();
        } else {
            User user = new User(nickname, password, LocalDateTime.now());
            userRepository.save(user);
            return Optional.of(user);
        }
    }
}
