package io.siliconsavannah.backend.mapper;

import io.siliconsavannah.backend.dto.PropertyDto;
import io.siliconsavannah.backend.model.Expense;
import io.siliconsavannah.backend.model.Lease;
import io.siliconsavannah.backend.model.Property;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.function.Function;

@Component
public class PropertyMapper implements Function<Property, PropertyDto> {
    @Override
    public PropertyDto apply(Property property) {
        return new PropertyDto(
            property.getId(),
            property.getAddress(),
            property.getUnit(),
            property.getCreatedAt(),
            property.getUpdatedAt(),
            property.getLease(),
            property.getExpense()
        );
    }
}