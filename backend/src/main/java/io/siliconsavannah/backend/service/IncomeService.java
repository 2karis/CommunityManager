package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.IncomeDto;
import io.siliconsavannah.backend.mapper.IncomeMapper;
import io.siliconsavannah.backend.model.Income;
import io.siliconsavannah.backend.repo.IncomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class IncomeService {
    @Autowired
    private IncomeMapper incomeMapper;
    @Autowired
    private IncomeRepo incomeRepo;

    public Income createIncome(Income income){
        return incomeRepo.save(income);
    }

    public List<IncomeDto> readAllIncomes(){
        return incomeRepo.findAll().stream().map(incomeMapper).collect(Collectors.toList());
    }

    public IncomeDto updateIncome(IncomeDto incomeDto){
        Optional<Income> income = incomeRepo.findById(incomeDto.id());
        income.ifPresent(
                el ->{
                    if (incomeDto.dueOn()!= null) el.setDueOn(incomeDto.dueOn());
                    if (incomeDto.paidOn()!= null) el.setPaidOn(incomeDto.paidOn());
                    if (incomeDto.status()!= null) el.setStatus(incomeDto.status());
                    if (incomeDto.amount()!= null) el.setAmount(incomeDto.amount());
                    if (incomeDto.lateFee()!= null) el.setLateFee(incomeDto.lateFee());
                    if (incomeDto.paid()!= null) el.setPaid(incomeDto.paid());
                    if (incomeDto.balance()!= null)el.setBalance(incomeDto.balance());
                    incomeRepo.save(el);
                }
        );
        return incomeDto;
    }
    public void deleteIncome(int id){
        incomeRepo.deleteById(id);
    }

    public IncomeDto findIncomeById(int id){
        return incomeRepo.findById(id).stream().map(incomeMapper).findAny().orElseThrow(RuntimeException::new);
    }
}