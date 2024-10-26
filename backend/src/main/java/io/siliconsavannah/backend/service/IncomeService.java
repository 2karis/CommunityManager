package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.IncomeDto;
import io.siliconsavannah.backend.mapper.IncomeMapper;
import io.siliconsavannah.backend.model.Income;
import io.siliconsavannah.backend.repo.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class IncomeService {
    @Autowired
    private IncomeMapper incomeMapper;
    @Autowired
    private IncomeRepository incomeRepository;


    public IncomeDto createIncome(IncomeDto income){
        return incomeMapper.entityToDto(incomeRepository.save(incomeMapper.dtoToEntity(income)));
    }

    public List<IncomeDto> readAllIncomes(){
        return incomeRepository.findAll().stream().map(incomeMapper::entityToDto).collect(Collectors.toList());
    }

    public IncomeDto updateIncome(IncomeDto dto) throws Exception {
        Income entity = incomeRepository.findIncomeById(dto.id())
                .orElseThrow(() -> new Exception("income with id "+ dto.id() +" not found"));

        if (dto.dueOn()!= null) entity.setDueOn(dto.dueOn());
        if (dto.paidOn()!= null) entity.setPaidOn(dto.paidOn());
        if (dto.status()!= null) entity.setStatus(dto.status());
        if (dto.amount()!= null) entity.setAmount(dto.amount());
        if (dto.lateFee()!= null) entity.setLateFee(dto.lateFee());
        if (dto.paid()!= null) entity.setPaid(dto.paid());
        if (dto.balance()!= null)entity.setBalance(dto.balance());

        return incomeMapper.entityToDto(incomeRepository.save(entity));
    }
    public void deleteIncome(int id){
        incomeRepository.deleteIncomeById(id);
    }

    public IncomeDto findIncomeById(int id) throws Exception {
        return incomeMapper.entityToDto(incomeRepository.findIncomeById(id)
                .orElseThrow(() -> new Exception("income with id "+ id +" not found")));
    }
}