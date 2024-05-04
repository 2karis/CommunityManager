package io.siliconsavannah.backend.dto;

import java.sql.Timestamp;

public record TaskDto(
        int id,
        String title,
        String type,
        String description,
        String image,
        String status,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}