package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.LeaseDto;
import io.siliconsavannah.backend.mapper.LeaseMapper;
import io.siliconsavannah.backend.model.Lease;
import io.siliconsavannah.backend.repo.LeaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LeaseService {
    @Autowired
    private LeaseMapper leaseMapper;
    @Autowired
    private LeaseRepo leaseRepo;

    public Lease createLease(Lease lease){
        return leaseRepo.save(lease);
    }

    public List<LeaseDto> readAllLeases(){
        return leaseRepo.findAll().stream().map(leaseMapper).collect(Collectors.toList());
    }

    public LeaseDto updateLease(LeaseDto leaseDto){
        Optional<Lease> lease = leaseRepo.findById(leaseDto.id());
        lease.ifPresent(
                el ->{
                    if (leaseDto.termFrom()!= null) el.setTermFrom(leaseDto.termFrom());
                    if (leaseDto.termTo()!= null) el.setTermTo(leaseDto.termTo());
                    if (leaseDto.rent()!= null) el.setRent(leaseDto.rent());
                    if (leaseDto.deposit()!= null) el.setDeposit(leaseDto.deposit());
                    if (leaseDto.status()!= null) el.setStatus(leaseDto.status());
                    if (leaseDto.file()!= null) el.setFile(leaseDto.file());
                    if (leaseDto.property()!= null) el.setProperty(leaseDto.property());
                    if (leaseDto.income()!= null) el.setIncome(leaseDto.income());
                    if (leaseDto.users()!= null) el.setUsers(leaseDto.users());
                    leaseRepo.save(el);
                }
        );
        return leaseDto;
    }
    public void deleteLease(int id){
        leaseRepo.deleteById(id);
    }

    public LeaseDto findLeaseById(int id){
        return leaseRepo.findById(id).stream().map(leaseMapper).findAny().orElseThrow(RuntimeException::new);
    }
}