package io.siliconsavannah.backend.mapper;

import io.siliconsavannah.backend.dto.ExpenseDto;
import io.siliconsavannah.backend.model.Expense;
import io.siliconsavannah.backend.model.Property;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.function.Function;

@Component
public class ExpenseMapper implements Function<Expense, ExpenseDto> {
    @Override
    public ExpenseDto apply(Expense expense) {
        return new ExpenseDto(
                expense.getId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getCreatedAt(),
                expense.getUpdatedAt(),
                expense.getProperty()
        );
    }
}
