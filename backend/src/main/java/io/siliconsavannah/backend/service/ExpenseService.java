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
    	Expense expense = expenseRepo.findById(expenseDto.id()).get();
        if (expenseDto.description()!= null) expense.setDescription(expenseDto.description());
        if (expenseDto.amount()!= null)expense.setAmount(expenseDto.amount());
        if (expenseDto.property()!= null)expense.setProperty(expenseDto.property());
        return Optional.of(expenseRepo.save(expense)).stream().map(expenseMapper).findFirst().get();
    }
    public void deleteExpense(int id){
        expenseRepo.deleteExpenseById(id);
    }

    public ExpenseDto findExpenseById(int id){
        return expenseRepo.findById(id).stream().map(expenseMapper).findAny().get();
    }
}