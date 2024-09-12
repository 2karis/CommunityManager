package io.siliconsavannah.backend.controller;

import io.siliconsavannah.backend.dto.LoginDto;
import io.siliconsavannah.backend.dto.SignUpDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/account")
public class AccountController {
    @GetMapping("/details")
    public ResponseEntity<?> getDetails(){
        try{
            return ResponseEntity.ok("retrieved Account details");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("failed to login User "+e.getMessage());
        }
    }
    @GetMapping("/invoices")
    public ResponseEntity<?> getInvoices(){
        try{
            return ResponseEntity.ok("All service requests successfully read");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/service-requests")
    public ResponseEntity<?> readAllServiceRequest(){
        try{
            return ResponseEntity.ok("All service requests successfully read");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/service-request")
    public ResponseEntity<?> readServiceRequest(){
        try{
            return ResponseEntity.ok("service request successfully read");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/service-request")
    public ResponseEntity<?> createServiceRequest(){
        try{
            return ResponseEntity.status(201).body("service request successfully created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/service-request")
    public ResponseEntity<?> updateServiceRequest(){
        try{
            return ResponseEntity.ok("service request successfully updated");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/service-request")
    public ResponseEntity<?> deleteServiceRequest(){
        try{
            return ResponseEntity.ok("service request successfully deleted");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/make-payment")
    public ResponseEntity<?> makePayment(){
        try{
            return ResponseEntity.ok("payment was successful");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
