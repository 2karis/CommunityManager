package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    void deleteUserById(int id);
    Optional<User> findUserById(int id);
}