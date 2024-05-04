package io.siliconsavannah.backend.controller;

import io.siliconsavannah.backend.dto.TaskDto;
import io.siliconsavannah.backend.dto.TaskDto;
import io.siliconsavannah.backend.model.Task;
import io.siliconsavannah.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskController {
    @Autowired
    public TaskService taskService;

    @GetMapping("/readall")
    public Flux<TaskDto> getAllTasks(){
        return taskService.readAllTasks();
    }

    @GetMapping("/read/{id}")
    public Mono<ResponseEntity<TaskDto>> getTask(@PathVariable("id") int id){
        return taskService.findTaskById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<TaskDto>> createTask(@RequestBody Mono<TaskDto> task){
        return taskService.createTask(task)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<TaskDto>> updateTask(@RequestBody Mono<TaskDto> task){
        return taskService.updateTask(task)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<?> deleteTask(@PathVariable("id") int id){
        return taskService.deleteTask(id);
    }
}