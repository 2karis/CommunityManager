package io.siliconsavannah.backend.mapper;

import io.siliconsavannah.backend.dto.ExpenseDto;
import io.siliconsavannah.backend.dto.IncomeDto;
import io.siliconsavannah.backend.model.Expense;
import io.siliconsavannah.backend.model.Income;
import io.siliconsavannah.backend.model.Lease;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface IncomeMapper {
    IncomeDto entityToDto(Income entity);
    Income dtoToEntity(IncomeDto dto);

}