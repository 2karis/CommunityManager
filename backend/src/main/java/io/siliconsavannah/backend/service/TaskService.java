package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.TaskDto;
import io.siliconsavannah.backend.dto.TaskDto;
import io.siliconsavannah.backend.dto.TaskDto;
import io.siliconsavannah.backend.mapper.TaskMapper;
import io.siliconsavannah.backend.model.Task;
import io.siliconsavannah.backend.repo.TaskRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskRepo taskRepo;

    public TaskDto createTask(TaskDto task){
        return taskMapper.entityToDto(taskRepo.save(taskMapper.dtoToEntity(task)));
    }

    public List<TaskDto> readAllTasks(){
        return taskRepo.findAll().stream().map(taskMapper::entityToDto).collect(Collectors.toList());
    }
    
    public TaskDto updateTask(TaskDto dto) throws Exception {
        Task entity = taskRepo.findTaskById(dto.id())
                .orElseThrow(() -> new Exception("task with id "+ dto.id() +" not found"));
        if (dto.title()!= null) entity.setTitle(dto.title());
        if (dto.type()!= null) entity.setType(dto.type());
        if (dto.description()!= null) entity.setDescription(dto.description());
        if (dto.image()!= null) entity.setImage(dto.image());
        if (dto.status()!= null) entity.setStatus(dto.status());

        return taskMapper.entityToDto(taskRepo.save(entity));
    }
    public void deleteTask(int id){
        taskRepo.deleteTaskById(id);
    }

    public TaskDto findTaskById(int id) throws Exception {
        return taskMapper.entityToDto(taskRepo.findTaskById(id)
                .orElseThrow(() -> new Exception("task with id "+ id +" not found")));
    }
}