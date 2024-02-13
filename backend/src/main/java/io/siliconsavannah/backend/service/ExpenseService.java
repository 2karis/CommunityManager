package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.ExpenseDto;
import io.siliconsavannah.backend.mapper.ExpenseMapper;
import io.siliconsavannah.backend.model.Expense;
import io.siliconsavannah.backend.repo.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExpenseService {
    @Autowired
    private ExpenseMapper expenseMapper;
    @Autowired
    private ExpenseRepo expenseRepo;

    public Expense createExpense(Expense expense){
        return expenseRepo.save(expense);
    }

    public List<ExpenseDto> readAllExpenses(){
        return expenseRepo.findAll().stream().map(expenseMapper).collect(Collectors.toList());
    }

    public ExpenseDto updateExpense(ExpenseDto expenseDto){
    	Optional<Expense> expense = expenseRepo.findById(expenseDto.id());
        expense.ifPresent(
                el ->{
                    if (expenseDto.description()!= null) el.setDescription(expenseDto.description());
                    if (expenseDto.amount()!= null)el.setAmount(expenseDto.amount());
                    if (expenseDto.property()!= null)el.setProperty(expenseDto.property());
                    expenseRepo.save(el);
                }
        );
        return expenseDto;
    }
    public void deleteExpense(int id){
        expenseRepo.deleteById(id);
    }

    public ExpenseDto findExpenseById(int id){
        return expenseRepo.findById(id).stream().map(expenseMapper).findAny().orElseThrow(RuntimeException::new);
    }
}