package com.example.WebSocketCoummunication.model;

import java.util.Optional;

public record UserWithInfo(Optional<User> user, Optional<UserInfo> userInfo) {}
