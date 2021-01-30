package com.example.restservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.service.LoanService;

@RestController
public class LoanController {
   
   private @Autowired LoanService loanSvc;
	
   @GetMapping("/loan/{id}")
   public Loan getLoan(@PathVariable("id") Long id) throws Exception {
      return Optional.ofNullable(id)
         .map(loanSvc::getLoan)
         .orElseThrow(() -> new Exception("Could not loan Loan"));
   }

   @GetMapping("/loan/{id}/metric")
   public LoanMetric getLoanMetric(@PathVariable("id") Long id) throws Exception {
      return Optional.ofNullable(id)
         .map(loanSvc::getLoan)
         .map(loanSvc::calculateLoanMetric)
         .orElseThrow(() -> new Exception("Could not calculate Loan Metric"));
   }
   
   @PostMapping("/loanmetric")
   public LoanMetric calculateLoanMetric(@RequestBody Loan loan) {
	     return loanSvc.calculateLoanMetric(loan);
   }

}
