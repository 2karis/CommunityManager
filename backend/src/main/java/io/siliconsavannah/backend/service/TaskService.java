package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.TaskDto;
import io.siliconsavannah.backend.dto.TaskDto;
import io.siliconsavannah.backend.mapper.TaskMapper;
import io.siliconsavannah.backend.model.Task;
import io.siliconsavannah.backend.model.Task;
import io.siliconsavannah.backend.repo.TaskRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskRepo taskRepo;

    public Task createTask(Task task){
        return taskRepo.save(task);
    }

    public Mono<TaskDto> createTask(Mono<TaskDto> taskDto){
        return taskDto
                .map(taskMapper::dtoToEntity)
                .map(taskRepo::save)
                .map(taskMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<TaskDto> readAllTasks(){
        return Flux.fromStream(() -> taskRepo.findAll().stream())
                .map(taskMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<TaskDto> updateTask(Mono<TaskDto> taskDto){

        return taskDto
                .flatMap(dto-> Mono.fromSupplier(()->taskRepo.findById(dto.id()))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .map(entity->{
                            if (dto.title()!= null) entity.setTitle(dto.title());
                            if (dto.type()!= null) entity.setType(dto.type());
                            if (dto.description()!= null) entity.setDescription(dto.description());
                            if (dto.image()!= null) entity.setImage(dto.image());
                            if (dto.status()!= null) entity.setStatus(dto.status());
                            return taskRepo.save(entity);
                        })
                )
                .filter(Objects::nonNull)

                .map(taskMapper::entityToDto)
                .switchIfEmpty(Mono.error(Throwable::new))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Void> deleteTask(int id){
        return Mono.fromRunnable(()->taskRepo.deleteById(id))
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<TaskDto> findTaskById(int id){
        return Mono.fromSupplier(()->taskRepo.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(taskMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
