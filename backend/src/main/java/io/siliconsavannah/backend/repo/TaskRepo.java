package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepo extends JpaRepository<Task, Integer> {
    void deleteTaskById(int id);

    Optional<Task> findTaskById(int id);
}
