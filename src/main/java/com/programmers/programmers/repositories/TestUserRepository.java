package com.programmers.programmers.repositories;

import com.programmers.programmers.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestUserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByLoginId(String loginId);
}
