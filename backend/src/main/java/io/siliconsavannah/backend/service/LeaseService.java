package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.LeaseDto;
import io.siliconsavannah.backend.mapper.LeaseMapper;
import io.siliconsavannah.backend.model.Income;
import io.siliconsavannah.backend.model.Lease;
import io.siliconsavannah.backend.repo.LeaseRepo;
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
public class LeaseService {
    @Autowired
    private LeaseMapper leaseMapper;
    @Autowired
    private LeaseRepo leaseRepo;

    public Mono<LeaseDto> createLease(Mono<LeaseDto> leaseDto){
        return leaseDto
                .map(leaseMapper::dtoToEntity)
                .map(leaseRepo::save)
                .map(leaseMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<LeaseDto> readAllLeases(){
        return Flux.fromStream(() -> leaseRepo.findAll().stream())
                .map(leaseMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<LeaseDto> updateLease(Mono<LeaseDto> leaseDto){

        return leaseDto
                .flatMap(dto-> Mono.fromSupplier(()->leaseRepo.findById(dto.id()))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .map(entity->{
                            if (dto.termFrom()!= null) entity.setTermFrom(dto.termFrom());
                            if (dto.termTo()!= null) entity.setTermTo(dto.termTo());
                            if (dto.rent()!= null) entity.setRent(dto.rent());
                            if (dto.deposit()!= null) entity.setDeposit(dto.deposit());
                            if (dto.status()!= null) entity.setStatus(dto.status());
                            if (dto.file()!= null) entity.setFile(dto.file());
                            if (dto.property()!= null) entity.setProperty(dto.property());
                            if (dto.income()!= null) entity.setIncome(dto.income());
                            if (dto.users()!= null) entity.setUsers(dto.users());
                            return leaseRepo.save(entity);
                        })
                )
                .filter(Objects::nonNull)
                .map(leaseMapper::entityToDto)
                .switchIfEmpty(Mono.error(Throwable::new))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Void> deleteLease(int id){
        return Mono.fromRunnable(()->{leaseRepo.deleteById(id);})
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<LeaseDto> findLeaseById(int id){
        return Mono.fromSupplier(()->leaseRepo.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(leaseMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }
}