package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseRepo extends JpaRepository<Expense, Integer> {
    void deleteExpenseById(int id);

    Optional<Expense> findExpenseById(int id);
}
