package com.example.restservice.metrics;

import java.util.Optional;

import org.springframework.util.ClassUtils;

import com.example.restservice.annotation.LoanMetricTemplate;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

public interface ILoanMetricCalculator {

   /**
    * Decides whether current Loan Metric Calculator instance supports the Loan entity
    * 
    * @param loan
    */
   public default boolean isSupported(Loan loan) {
      return 
         Optional.ofNullable(loan)
            .filter(l -> l.getLoanId() != null)
            .filter(l -> l.getRequestedAmount() > 0)
            //.filter(l -> l.getTermMonths() > 0)
            .isPresent() &&
         Optional.ofNullable(ClassUtils.getUserClass(this))
            .filter(c -> c.isAnnotationPresent(LoanMetricTemplate.class))
            .map(c -> c.getAnnotation(LoanMetricTemplate.class).value())
            .map(v -> v.equalsIgnoreCase(loan.getType()))
            .isPresent();
      }
   
   /**
    * Calculates the Loan Metric of a Loan entity
    * 
    * @param loan
    */
   public LoanMetric getLoanMetric(Loan loan);
   
}
