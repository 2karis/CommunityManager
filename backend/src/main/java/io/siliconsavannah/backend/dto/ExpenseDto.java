package io.siliconsavannah.backend.dto;

import io.siliconsavannah.backend.model.Property;
import java.math.BigDecimal;
import java.sql.Timestamp;

public record ExpenseDto(
        int id,
        String description,
        BigDecimal amount,
        Timestamp createdAt,
        Timestamp updatedAt,
        Property property

) {
}
