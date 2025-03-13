package com.gsm._8th.class4.backend.task12.domain.auth.Repository;

import com.gsm._8th.class4.backend.task12.domain.auth.Entity.NewSign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<NewSign, Long> {

    Optional<NewSign> findByUsername(String username);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);
}
