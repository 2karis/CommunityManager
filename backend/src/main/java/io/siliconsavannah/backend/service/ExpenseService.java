package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.ExpenseDto;
import io.siliconsavannah.backend.mapper.ExpenseMapper;
import io.siliconsavannah.backend.model.Expense;
import io.siliconsavannah.backend.repo.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ExpenseService {
    @Autowired
    public ExpenseMapper expenseMapper;
    @Autowired
    private ExpenseRepo expenseRepo;

    public Mono<ExpenseDto> createExpense(Mono<ExpenseDto> expenseDto){
        return expenseDto
                .map(expenseMapper::dtoToEntity)
                .map(expenseRepo::save)
                .map(expenseMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<ExpenseDto> readAllExpenses() {
        return Flux.fromStream(() -> expenseRepo.findAll().stream())
                .map(expenseMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());

    }
        public Mono<ExpenseDto> updateExpense(Mono<ExpenseDto> expenseDto){
        return expenseDto
                .flatMap(dto-> Mono.fromSupplier(()->expenseRepo.findById(dto.id()))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .map(entity->{
                            if (dto.description()!= null) entity.setDescription(dto.description());
                            if (dto.amount()!= null)entity.setAmount(dto.amount());
                            if (dto.property()!= null)entity.setProperty(dto.property());
                            return expenseRepo.save(entity);
                        })
                )
                .filter(Objects::nonNull)
                    .map(expenseMapper::entityToDto)
                    .switchIfEmpty(Mono.error(Throwable::new))
                    .subscribeOn(Schedulers.boundedElastic());
    }
    public Mono<Void> deleteExpense(int id){
        return Mono.fromRunnable(()->{expenseRepo.deleteById(id);})
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<ExpenseDto> findExpenseById(int id){
        return Mono.fromSupplier(()->expenseRepo.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(expenseMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }
}