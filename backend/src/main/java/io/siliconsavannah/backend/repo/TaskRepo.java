package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Integer> {
}
