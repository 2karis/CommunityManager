package io.siliconsavannah.backend.controller;

import io.siliconsavannah.backend.dto.PropertyDto;
import io.siliconsavannah.backend.dto.PropertyDto;
import io.siliconsavannah.backend.model.Property;
import io.siliconsavannah.backend.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/property")
public class PropertyController {
    @Autowired
    public PropertyService propertyService;

    @GetMapping("/readall")
    public Flux<PropertyDto> getAllPropertys(){
        return propertyService.readAllPropertys();
    }

    @GetMapping("/read/{id}")
    public Mono<ResponseEntity<PropertyDto>> getProperty(@PathVariable("id") int id){
        return propertyService.findPropertyById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<PropertyDto>> createProperty(@RequestBody Mono<PropertyDto> property){
        return propertyService.createProperty(property)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<PropertyDto>> updateProperty(@RequestBody Mono<PropertyDto> property){
        return propertyService.updateProperty(property)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<?> deleteProperty(@PathVariable("id") int id){
        return propertyService.deleteProperty(id);
    }
}