package io.siliconsavannah.backend.controller;

import io.siliconsavannah.backend.dto.ExpenseDto;
import io.siliconsavannah.backend.mapper.ExpenseMapper;
import io.siliconsavannah.backend.model.Expense;
import io.siliconsavannah.backend.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    public ExpenseService expenseService;
    @GetMapping("/readall")
    public Flux<ExpenseDto> getAllExpenses(){
        return expenseService.readAllExpenses();
    }

    @GetMapping("/read/{id}")
    public Mono<ResponseEntity<ExpenseDto>> getExpense(@PathVariable("id") int id){
        return expenseService.findExpenseById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<ExpenseDto>> createExpense(@RequestBody Mono<ExpenseDto> expense){
        return expenseService.createExpense(expense)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<ExpenseDto>> updateExpense(@RequestBody Mono<ExpenseDto> expense){
        return expenseService.updateExpense(expense)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<?> deleteExpense(@PathVariable("id") int id){
        return expenseService.deleteExpense(id);
    }
}
