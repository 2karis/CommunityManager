package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.Expense;
import io.siliconsavannah.backend.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface IncomeRepo extends JpaRepository<Income, Integer> {
    void deleteIncomeById(int id);

    Optional<Income> findIncomeById(int id);
}
