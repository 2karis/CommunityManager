package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.TaskDto;
import io.siliconsavannah.backend.mapper.TaskMapper;
import io.siliconsavannah.backend.model.Task;
import io.siliconsavannah.backend.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskRepo taskRepo;

    public Task createTask(Task task){
        return taskRepo.save(task);
    }

    public List<TaskDto> readAllTasks(){
        return taskRepo.findAll().stream().map(taskMapper).collect(Collectors.toList());
    }

    public TaskDto updateTask(TaskDto taskDto){
        Optional<Task> task = taskRepo.findById(taskDto.id());
        task.ifPresent(
                el ->{
                    if (taskDto.title()!= null) el.setTitle(taskDto.title());
                    if (taskDto.type()!= null) el.setType(taskDto.type());
                    if (taskDto.description()!= null) el.setDescription(taskDto.description());
                    if (taskDto.image()!= null) el.setImage(taskDto.image());
                    taskRepo.save(el);
                }
        );
        return taskDto;
    }
    public void deleteTask(int id){
        taskRepo.deleteById(id);
    }

    public TaskDto findTaskById(int id){
        return taskRepo.findById(id).stream().map(taskMapper).findAny().orElseThrow(RuntimeException::new);
    }
}
