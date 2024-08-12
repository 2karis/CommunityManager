package io.siliconsavannah.backend.controller;

import io.siliconsavannah.backend.dto.PropertyDto;
import io.siliconsavannah.backend.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/management/property")
public class PropertyController {
    @Autowired
    public PropertyService propertyService;

    @GetMapping("/readall")
    public ResponseEntity<List<PropertyDto>> getAllPropertys(){
        return new ResponseEntity<>(propertyService.readAllPropertys(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<PropertyDto> getProperty(@PathVariable("id") int id){
        try {
            return new ResponseEntity<>(propertyService.findPropertyById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<PropertyDto> createProperty(@RequestBody PropertyDto property){
        return new ResponseEntity<>(propertyService.createProperty(property), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<PropertyDto>updateProperty(@RequestBody PropertyDto property){
        try {
            return new ResponseEntity<>(propertyService.updateProperty(property), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable("id") int id){
        propertyService.deleteProperty(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}