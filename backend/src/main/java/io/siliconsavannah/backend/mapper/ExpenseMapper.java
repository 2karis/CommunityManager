package io.siliconsavannah.backend.mapper;

import io.siliconsavannah.backend.dto.ExpenseDto;
import io.siliconsavannah.backend.model.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper (componentModel = "spring", uses = {PropertyMapper.class})
public interface ExpenseMapper  {
    ExpenseMapper mapper = Mappers.getMapper(ExpenseMapper.class);
    Expense mapDtoToEntity(ExpenseDto source);
    ExpenseDto mapEntityToDto(Expense destination);

    default Expense dtoToEntity(ExpenseDto dto) {
        return mapDtoToEntity(dto);
    }
    default ExpenseDto entityToDto(Expense entity) {
        return mapEntityToDto(entity);
    }
}