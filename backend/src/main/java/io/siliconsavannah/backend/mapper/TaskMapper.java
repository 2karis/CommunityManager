package io.siliconsavannah.backend.mapper;

import io.siliconsavannah.backend.dto.LeaseDto;
import io.siliconsavannah.backend.dto.TaskDto;
import io.siliconsavannah.backend.model.Lease;
import io.siliconsavannah.backend.model.Task;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.function.Function;


@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto entityToDto(Task entity);
    Task dtoToEntity(TaskDto dto);

}