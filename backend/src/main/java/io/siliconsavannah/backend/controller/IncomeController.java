package io.siliconsavannah.backend.controller;

import io.siliconsavannah.backend.dto.IncomeDto;
import io.siliconsavannah.backend.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/income")
public class IncomeController {
    @Autowired
    public IncomeService incomeService;

    @GetMapping("/readall")
    public Flux<IncomeDto> getAllIncomes(){
        return incomeService.readAllIncomes();
    }

    @GetMapping("/read/{id}")
    public Mono<ResponseEntity<IncomeDto>> getIncome(@PathVariable("id") int id){
        return incomeService.findIncomeById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<IncomeDto>> createIncome(@RequestBody Mono<IncomeDto> income){
        return incomeService.createIncome(income)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<IncomeDto>> updateIncome(@RequestBody Mono<IncomeDto> income){
        return incomeService.updateIncome(income)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<?> deleteIncome(@PathVariable("id") int id){
        return incomeService.deleteIncome(id);
    }
}