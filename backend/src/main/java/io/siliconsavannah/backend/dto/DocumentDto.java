package io.siliconsavannah.backend.dto;

import java.sql.Timestamp;

public record DocumentDto (
        int id,
        String filename,
        String fileLocation,
        Timestamp createdAt,
        Timestamp updatedAt
){
}
