package io.siliconsavannah.backend.controller;

import io.siliconsavannah.backend.dto.LeaseDto;
import io.siliconsavannah.backend.model.Lease;
import io.siliconsavannah.backend.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ={"http://localhost:4200", "http://localhost:80"})
@RequestMapping("/lease")
public class LeaseController {
    @Autowired
    public LeaseService LeaseService;

    @GetMapping("/readall")
    public ResponseEntity<List<LeaseDto>> getAllLeases(){
        return new ResponseEntity<>(LeaseService.readAllLeases(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<LeaseDto> getLease(@PathVariable("id") int id){
        return new ResponseEntity<>(LeaseService.findLeaseById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Lease> createLease(@RequestBody Lease Lease){
        return new ResponseEntity<>(LeaseService.createLease(Lease), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<LeaseDto>updateLease(@RequestBody LeaseDto Lease){
        return new ResponseEntity<>(LeaseService.updateLease(Lease), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLease(@PathVariable("id") int id){
        LeaseService.deleteLease(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}