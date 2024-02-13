package io.siliconsavannah.backend.mapper;

import io.siliconsavannah.backend.dto.TaskDto;
import io.siliconsavannah.backend.model.Task;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.function.Function;

@Component
public class TaskMapper implements Function<Task, TaskDto> {
    @Override
    public TaskDto apply(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getType(),
                task.getDescription(),
                task.getImage(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }
}