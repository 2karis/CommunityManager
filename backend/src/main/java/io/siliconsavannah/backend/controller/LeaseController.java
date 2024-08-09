package io.siliconsavannah.backend.controller;

import io.siliconsavannah.backend.dto.LeaseDto;
import io.siliconsavannah.backend.dto.LeaseDto;
import io.siliconsavannah.backend.model.Lease;
import io.siliconsavannah.backend.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/lease")
public class LeaseController {
    @Autowired
    public LeaseService leaseService;

    @GetMapping("/readall")
    public ResponseEntity<List<LeaseDto>> getAllLeases(){
        return new ResponseEntity<>(leaseService.readAllLeases(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<LeaseDto> getLease(@PathVariable("id") int id){
        try {
            return new ResponseEntity<>(leaseService.findLeaseById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<LeaseDto> createLease(@RequestBody LeaseDto lease){
        return new ResponseEntity<>(leaseService.createLease(lease), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<LeaseDto>updateLease(@RequestBody LeaseDto lease){
        try {
            return new ResponseEntity<>(leaseService.updateLease(lease), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLease(@PathVariable("id") int id){
        leaseService.deleteLease(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}