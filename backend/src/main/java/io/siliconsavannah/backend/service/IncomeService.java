package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.IncomeDto;
import io.siliconsavannah.backend.mapper.IncomeMapper;
import io.siliconsavannah.backend.model.Income;
import io.siliconsavannah.backend.repo.IncomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class IncomeService {
    @Autowired
    private IncomeMapper incomeMapper;
    @Autowired
    private IncomeRepo incomeRepo;

    public Mono<IncomeDto> createIncome(Mono<IncomeDto> incomeDto){
        return incomeDto
                .map(incomeMapper::dtoToEntity)
                .map(incomeRepo::save)
                .map(incomeMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<IncomeDto> readAllIncomes(){
        return Flux.fromStream(() -> incomeRepo.findAll().stream())
                .map(incomeMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<IncomeDto> updateIncome(Mono<IncomeDto> incomeDto){
        return incomeDto
                .flatMap(dto-> Mono.fromSupplier(()->incomeRepo.findById(dto.id()))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .map(entity->{
                    if (dto.dueOn()!= null) entity.setDueOn(dto.dueOn());
                    if (dto.paidOn()!= null) entity.setPaidOn(dto.paidOn());
                    if (dto.status()!= null) entity.setStatus(dto.status());
                    if (dto.amount()!= null) entity.setAmount(dto.amount());
                    if (dto.lateFee()!= null) entity.setLateFee(dto.lateFee());
                    if (dto.paid()!= null) entity.setPaid(dto.paid());
                    if (dto.balance()!= null)entity.setBalance(dto.balance());
                    return incomeRepo.save(entity);
                        })
                )
                .filter(Objects::nonNull)
                .map(incomeMapper::entityToDto)
                .switchIfEmpty(Mono.error(Throwable::new))
                .subscribeOn(Schedulers.boundedElastic());

    }
    public Mono<Void> deleteIncome(int id){
        return Mono.fromRunnable(()->{incomeRepo.deleteById(id);})
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<IncomeDto> findIncomeById(int id){
        return Mono.fromSupplier(()->incomeRepo.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(incomeMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }
}