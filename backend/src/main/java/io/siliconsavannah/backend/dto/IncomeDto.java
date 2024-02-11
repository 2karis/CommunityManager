package io.siliconsavannah.backend.dto;

import io.siliconsavannah.backend.model.Lease;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public record IncomeDto(
        int id,
        Date dueOn,
        Date paidOn,
        String status,
        BigDecimal amount,
        BigDecimal lateFee,
        BigDecimal paid,
        BigDecimal balance,
        Timestamp createdAt,
        Timestamp updatedAt,
        Lease lease
) {
}
