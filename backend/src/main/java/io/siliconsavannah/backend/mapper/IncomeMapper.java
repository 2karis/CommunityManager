package io.siliconsavannah.backend.mapper;

import io.siliconsavannah.backend.dto.IncomeDto;
import io.siliconsavannah.backend.model.Income;
import io.siliconsavannah.backend.model.Lease;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.function.Function;

@Component
public class IncomeMapper implements Function<Income, IncomeDto> {
    @Override
    public IncomeDto apply(Income income) {
        return new IncomeDto(
            income.getId(),
            income.getDueOn(),
            income.getPaidOn(),
            income.getStatus(),
            income.getAmount(),
            income.getLateFee(),
            income.getPaid(),
            income.getBalance(),
            income.getCreatedAt(),
            income.getUpdatedAt(),
            income.getLease()
        );
    }
}