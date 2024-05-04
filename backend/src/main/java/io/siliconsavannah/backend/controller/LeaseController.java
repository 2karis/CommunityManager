package io.siliconsavannah.backend.controller;

import io.siliconsavannah.backend.dto.IncomeDto;
import io.siliconsavannah.backend.dto.LeaseDto;
import io.siliconsavannah.backend.model.Lease;
import io.siliconsavannah.backend.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@CrossOrigin
@RequestMapping("/lease")
public class LeaseController {
    @Autowired
    public LeaseService leaseService;

    @GetMapping("/readall")
    public Flux<LeaseDto> getAllLeases(){
        return leaseService.readAllLeases();
    }

    @GetMapping("/read/{id}")
    public Mono<ResponseEntity<LeaseDto>> getLease(@PathVariable("id") int id){
        return leaseService.findLeaseById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<LeaseDto>> createLease(@RequestBody Mono<LeaseDto> lease){
        return leaseService.createLease(lease)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<LeaseDto>> updateLease(@RequestBody Mono<LeaseDto> lease){
        return leaseService.updateLease(lease)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<?> deleteLease(@PathVariable("id") int id){
        return leaseService.deleteLease(id);
    }
}