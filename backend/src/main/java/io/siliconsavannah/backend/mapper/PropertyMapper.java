package io.siliconsavannah.backend.mapper;

import io.siliconsavannah.backend.dto.LeaseDto;
import io.siliconsavannah.backend.dto.PropertyDto;
import io.siliconsavannah.backend.model.Expense;
import io.siliconsavannah.backend.model.Lease;
import io.siliconsavannah.backend.model.Property;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.function.Function;


@Mapper(componentModel = "spring")
public interface PropertyMapper {
    PropertyDto entityToDto(Property entity);
    Property dtoToEntity(PropertyDto dto);

}