package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    void deleteUserById(int id);
    Optional<User> findUserById(int id);
    Optional<User> findFirstByEmail(String email);

}