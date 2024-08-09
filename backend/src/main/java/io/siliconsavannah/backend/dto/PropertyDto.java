package io.siliconsavannah.backend.dto;

import io.siliconsavannah.backend.model.Expense;
import io.siliconsavannah.backend.model.Lease;

import java.sql.Timestamp;
import java.util.HashSet;

public record PropertyDto (
        int id,
        String address,
        String unit,
        Timestamp createdAt,
        Timestamp updatedAt,
//        HashSet<Lease> lease,
        HashSet<Expense> expense
){
}
