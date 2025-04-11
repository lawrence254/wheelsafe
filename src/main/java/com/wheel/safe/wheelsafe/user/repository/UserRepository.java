package com.wheel.safe.wheelsafe.user.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.wheel.safe.wheelsafe.user.entity.User;

@Repository
public interface UserRepository {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
