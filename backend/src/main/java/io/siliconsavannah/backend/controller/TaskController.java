package io.siliconsavannah.backend.controller;

import io.siliconsavannah.backend.dto.TaskDto;
import io.siliconsavannah.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    public TaskService taskService;

    @GetMapping("/readall")
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        return new ResponseEntity<>(taskService.readAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("id") int id){
        try {
            return new ResponseEntity<>(taskService.findTaskById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto task){
        return new ResponseEntity<>(taskService.createTask(task), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<TaskDto>updateTask(@RequestBody TaskDto task){
        try {
            return new ResponseEntity<>(taskService.updateTask(task), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") int id){
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}