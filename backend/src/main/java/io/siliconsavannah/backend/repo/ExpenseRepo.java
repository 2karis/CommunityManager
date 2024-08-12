package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.Expense;
import io.siliconsavannah.backend.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface ExpenseRepo extends JpaRepository<Expense, Integer> {
    void deleteExpenseById(int id);

    Optional<Expense> findExpenseById(int id);
}
