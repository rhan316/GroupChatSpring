package com.example.WebSocketCoummunication.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByNick(String nick);
    Optional<UserInfo> findByNickAndEmail(String nick, String email);
}
