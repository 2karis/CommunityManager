package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.Expense;
import io.siliconsavannah.backend.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IncomeRepo extends JpaRepository<Income, Integer> {
}
