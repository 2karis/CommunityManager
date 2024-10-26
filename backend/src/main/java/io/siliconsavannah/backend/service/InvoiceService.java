package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.model.Invoice;
import io.siliconsavannah.backend.model.Lease;
import io.siliconsavannah.backend.repo.InvoiceRepository;
import io.siliconsavannah.backend.repo.LeaseRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private BillingItemService billingItemService;



    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
    @SneakyThrows
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Transactional
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    // todo - invoices will be available 15 days before due date
    // todo - i may have to save the invoice first before creating the billing item so i can get the invoice id, we'll see
//    @Scheduled(cron = "0 0 0 * * ?") // Runs every day at midnight
//    @Transactional
//    public void generateInvoices() {
//        LocalDate today = LocalDate.now();
//        List<Lease> activeLeases = leaseRepository.findActiveLeases(today);
//
//        for (Lease lease : activeLeases) {
//            Invoice invoice = Invoice.builder()
//                    .issueDate(today)
//                    .dueDate(today.plusDays(30))
//                    .totalAmount(BigDecimal.valueOf(lease.getRent()))
//                    .lease(lease)
//                    .build();
//            invoice.setIssueDate(today);
//            invoice.setDueDate(today.plusDays(30)); // Example due date
//            invoice.setTotalAmount(BigDecimal.valueOf(lease.getRent()));
//            invoice.setLease(lease);
//            addBillingItem(invoice,"Monthly Fee", BigDecimal.valueOf(lease.getRent()));
//
//            invoiceRepository.save(invoice);
//        }
//    }
    private void addBillingItem(Invoice invoice,String description, BigDecimal monthlyFee){
        billingItemService.saveBillingItem(invoice, description, monthlyFee);
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?") // Runs every day at midnight
    public void applyLateFees() {
        LocalDate today = LocalDate.now();
        List<Invoice> pastDueInvoices = invoiceRepository.findPastDueInvoices(today);

        for (Invoice invoice : pastDueInvoices) {
            BigDecimal lateFee = calculateLateFee(invoice.getTotalAmount());
            addBillingItem(invoice,"Late Fee", lateFee);
            invoice.setTotalAmount(invoice.getTotalAmount().add(lateFee));
            invoiceRepository.save(invoice);
        }
    }

    private BigDecimal calculateLateFee(BigDecimal totalAmount) {
        // Example: 5% late fee or $100
        return totalAmount.multiply(BigDecimal.valueOf(0.05));
    }

    // todo - manually update invoices and billing items if needed
    public void updateInvoice(){

    }
}
