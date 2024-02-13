package io.siliconsavannah.backend.mapper;

import io.siliconsavannah.backend.dto.LeaseDto;
import io.siliconsavannah.backend.model.Income;
import io.siliconsavannah.backend.model.Lease;
import io.siliconsavannah.backend.model.Property;
import io.siliconsavannah.backend.model.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.function.Function;

@Component
public class LeaseMapper implements Function<Lease, LeaseDto> {
    @Override
    public LeaseDto apply(Lease lease) {
        return new LeaseDto(

                lease.getId(),
                lease.getTermFrom(),
                lease.getTermTo(),
                lease.getRent(),
                lease.getDeposit(),
                lease.getStatus(),
                lease.getFile(),
                lease.getCreatedAt(),
                lease.getUpdatedAt(),
                lease.getProperty(),
                lease.getIncome(),
                lease.getUsers()
        );
    }
}