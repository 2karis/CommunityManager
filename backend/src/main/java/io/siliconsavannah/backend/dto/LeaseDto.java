package io.siliconsavannah.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.siliconsavannah.backend.model.Document;
import io.siliconsavannah.backend.model.Income;
import io.siliconsavannah.backend.model.Property;
import io.siliconsavannah.backend.model.User;
import org.springframework.format.annotation.DateTimeFormat;


import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;

public record LeaseDto(
    int id,
    @JsonFormat(pattern="yyyy-MM-dd")
    SimpleDateFormat termFrom,
    @JsonFormat(pattern="yyyy-MM-dd")
    SimpleDateFormat  termTo,
    int rent,
    int deposit,
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
