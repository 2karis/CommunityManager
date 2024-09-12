package io.siliconsavannah.backend.job;

import io.siliconsavannah.backend.service.IncomeService;
import io.siliconsavannah.backend.service.LeaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class LeaseJob {
    private final LeaseService leaseService;
    private final IncomeService incomeService;
    @Scheduled(cron = "0 0 0 * * *") // everyday at midnight
    public void ProcessIncome(){
        log.info("processing income");
    }
}
