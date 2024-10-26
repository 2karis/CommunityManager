package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.LeaseDto;
import io.siliconsavannah.backend.mapper.LeaseMapper;
import io.siliconsavannah.backend.model.Lease;
import io.siliconsavannah.backend.model.User;
import io.siliconsavannah.backend.repo.LeaseRepository;
import io.siliconsavannah.backend.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class LeaseService {
    @Autowired
    private LeaseMapper leaseMapper;
    @Autowired
    private LeaseRepository leaseRepository;
    @Autowired
    private UserRepository userRepository;


    public LeaseDto createLease(LeaseDto lease){
        return leaseMapper.entityToDto(leaseRepository.save(leaseMapper.dtoToEntity(lease)));
    }

    public List<LeaseDto> readAllLeases(){
        return leaseRepository.findAll().stream().map(leaseMapper::entityToDto).collect(Collectors.toList());
    }

    public LeaseDto updateLease(LeaseDto dto) throws Exception {
        Lease entity = leaseRepository.findLeaseById(dto.id())
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


        return leaseMapper.entityToDto(leaseRepository.save(entity));
    }
    public void deleteLease(int id){
        leaseRepository.deleteLeaseById(id);
    }

    public LeaseDto findLeaseById(int id) throws Exception {
        return leaseMapper.entityToDto(leaseRepository.findLeaseById(id)
                .orElseThrow(() -> new Exception("lease with id "+ id +" not found")));
    }
//    public Lease createLease(LeaseDto leaseDto) {
//        LocalDate startDate = LocalDate.now();
//        LocalDate endDate = calculateEndDate(startDate);
//        User user = userRepository.findById(leaseDto.userId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Lease lease = Lease.builder()
//                .user(user)
//                .monthlyFee(leaseDto.monthlyFee())
//                .startDate(LocalDate.now())
//                .build();
//
//        // Optionally, calculate and set the end date based on business rules
//        // Save the lease to the database
//        if(hasOverlappingLeases(leaseDto.userId(),startDate,endDate)){
//            throw new RuntimeException("Overlapping Leases");
//
//        }
//        return leaseRepository.save(lease);
//
//    }

    public LocalDate calculateEndDate(LocalDate startDate) {
        // Assuming a monthly lease
        return startDate.plusYears(1); // Add one month to the start date
    }
//    public boolean hasOverlappingLeases(int userId, LocalDate newStartDate, LocalDate newEndDate) {
//        int count = leaseRepository.overlappingLeases(userId,newStartDate,newEndDate);
//        return count > 0;
//    }
}