package com.gsm._8th.class4.backend.task12.domain.auth.repository;

import com.gsm._8th.class4.backend.task12.domain.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
