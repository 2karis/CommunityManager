package io.siliconsavannah.backend.controller;

import io.siliconsavannah.backend.dto.IncomeDto;
import io.siliconsavannah.backend.model.Income;
import io.siliconsavannah.backend.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ={"http://localhost:4200", "http://localhost:80"})
@RequestMapping("/income")
public class IncomeController {
    @Autowired
    public IncomeService incomeService;

    @GetMapping("/readall")
    public ResponseEntity<List<IncomeDto>> getAllIncomes(){
        return new ResponseEntity<>(incomeService.readAllIncomes(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<IncomeDto> getIncome(@PathVariable("id") int id){
        return new ResponseEntity<>(incomeService.findIncomeById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Income> createIncome(@RequestBody Income income){
        return new ResponseEntity<>(incomeService.createIncome(income), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<IncomeDto>updateIncome(@RequestBody IncomeDto income){
        return new ResponseEntity<>(incomeService.updateIncome(income), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable("id") int id){
        incomeService.deleteIncome(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}