package io.siliconsavannah.backend.mapper;

import io.siliconsavannah.backend.dto.IncomeDto;
import io.siliconsavannah.backend.dto.LeaseDto;
import io.siliconsavannah.backend.model.Income;
import io.siliconsavannah.backend.model.Lease;
import io.siliconsavannah.backend.model.Property;
import io.siliconsavannah.backend.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.function.Function;

@Mapper(componentModel = "spring")
//@Component
public interface LeaseMapper {
    LeaseDto entityToDto(Lease entity);
    Lease dtoToEntity(LeaseDto dto);

}