package io.siliconsavannah.backend.dto;

import io.siliconsavannah.backend.model.Document;
import io.siliconsavannah.backend.model.Income;
import io.siliconsavannah.backend.model.Property;
import io.siliconsavannah.backend.model.User;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;

public record LeaseDto(
    int id,
    Date termFrom,
    Date termTo,
    BigDecimal rent,
    BigDecimal deposit,
    String status,
    String file,
    Timestamp createdAt,
    Timestamp updatedAt,
    Property property,
    HashSet<Income> income,
    HashSet<User> users
    //HashSet<DocumentDto> documents
) {
}
