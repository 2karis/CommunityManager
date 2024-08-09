package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.LeaseDto;
import io.siliconsavannah.backend.dto.LeaseDto;
import io.siliconsavannah.backend.mapper.LeaseMapper;
import io.siliconsavannah.backend.model.Lease;
import io.siliconsavannah.backend.model.Lease;
import io.siliconsavannah.backend.repo.LeaseRepo;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class LeaseService {
    @Autowired
    private LeaseMapper leaseMapper;
    @Autowired
    private LeaseRepo leaseRepo;

    public LeaseDto createLease(LeaseDto lease){
        return leaseMapper.entityToDto(leaseRepo.save(leaseMapper.dtoToEntity(lease)));
    }

    public List<LeaseDto> readAllLeases(){
        return leaseRepo.findAll().stream().map(leaseMapper::entityToDto).collect(Collectors.toList());
    }

    public LeaseDto updateLease(LeaseDto dto) throws Exception {
        Lease entity = leaseRepo.findLeaseById(dto.id())
                .orElseThrow(() -> new Exception("lease with id "+ dto.id() +" not found"));

        if (dto.termFrom()!= null) entity.setTermFrom(dto.termFrom());
        if (dto.termTo()!= null) entity.setTermTo(dto.termTo());
        if (dto.rent()!= 0) entity.setRent(dto.rent());
        if (dto.deposit()!= 0) entity.setDeposit(dto.deposit());
        if (dto.status()!= null) entity.setStatus(dto.status());
        if (dto.file()!= null) entity.setFile(dto.file());
        if (dto.property()!= null) entity.setProperty(dto.property());
        if (dto.income()!= null) entity.setIncome(dto.income());
        if (dto.users()!= null) entity.setUsers(dto.users());


        return leaseMapper.entityToDto(leaseRepo.save(entity));
    }
    public void deleteLease(int id){
        leaseRepo.deleteLeaseById(id);
    }

    public LeaseDto findLeaseById(int id) throws Exception {
        return leaseMapper.entityToDto(leaseRepo.findLeaseById(id)
                .orElseThrow(() -> new Exception("lease with id "+ id +" not found")));
    }
}