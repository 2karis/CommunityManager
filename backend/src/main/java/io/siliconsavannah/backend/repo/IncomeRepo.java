package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepo extends JpaRepository<Income, Integer> {
}
