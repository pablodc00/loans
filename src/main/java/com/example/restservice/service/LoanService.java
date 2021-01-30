package com.example.restservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.metrics.LoanMetricFactory;
import com.example.restservice.model.Borrower;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

@Service
public class LoanService {
   
   @SuppressWarnings("unused")
   private @Autowired LoanMetricFactory factory;
   
   private Borrower borrower = null;
   private Loan loan = null;
   
   public Loan getLoan(Long id) {
	   return null;
   }
   
   public LoanMetric calculateLoanMetric(Loan loan) {
	   return factory.getInstance(loan).getLoanMetric(loan);
   }

}
