package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
