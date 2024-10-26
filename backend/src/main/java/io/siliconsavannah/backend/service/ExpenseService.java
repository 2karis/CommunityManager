package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.ExpenseDto;
import io.siliconsavannah.backend.mapper.ExpenseMapper;
import io.siliconsavannah.backend.model.Expense;
import io.siliconsavannah.backend.repo.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExpenseService {
    @Autowired
    public ExpenseMapper expenseMapper;
    @Autowired
    private ExpenseRepository expenseRepository;

    public ExpenseDto createExpense(ExpenseDto expense){
        return expenseMapper.entityToDto(expenseRepository.save(expenseMapper.dtoToEntity(expense)));
    }

    public List<ExpenseDto> readAllExpenses(){
        return expenseRepository.findAll().stream().map(expenseMapper::entityToDto).collect(Collectors.toList());
    }

    public ExpenseDto updateExpense(ExpenseDto dto) throws Exception {
        Expense entity = expenseRepository.findExpenseById(dto.id())
                .orElseThrow(() -> new Exception("expense with id "+ dto.id() +" not found"));

        if (dto.description()!= null) entity.setDescription(dto.description());
        if (dto.amount()!= null)entity.setAmount(dto.amount());
        if (dto.property()!= null)entity.setProperty(dto.property());


        return expenseMapper.entityToDto(expenseRepository.save(entity));
    }
    public void deleteExpense(int id){
        expenseRepository.deleteExpenseById(id);
    }

    public ExpenseDto findExpenseById(int id) throws Exception {
        return expenseMapper.entityToDto(expenseRepository.findExpenseById(id)
                .orElseThrow(() -> new Exception("expense with id "+ id +" not found")));
    }
}