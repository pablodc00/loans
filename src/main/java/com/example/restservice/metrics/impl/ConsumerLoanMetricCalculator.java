package com.example.restservice.metrics.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.restservice.annotation.LoanMetricTemplate;
import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

@Component
@LoanMetricTemplate("consumer")
public class ConsumerLoanMetricCalculator implements ILoanMetricCalculator {

   @Override
   public boolean isSupported(Loan loan) {
      return ILoanMetricCalculator.super.isSupported(loan);
      /*
      &&
         Optional.ofNullable(loan)
         .map(Loan::getType)
         .filter(t -> t.equals("consumer"))
         .isPresent();
      */
      
      	/*
         	.map(Loan::getBorrower)
            .map(Borrower::getAge)
            .filter(a -> a >= 21) // greater than 21!
            .isPresent();
        */
   }	
	
   @Override
   public LoanMetric getLoanMetric(Loan loan) {
	   double monthlyRate = (loan.getAnnualInterest() /12 );
	   double monthlyPayment = (loan.getRequestedAmount() * monthlyRate) / 
			   (1 - (1 + Math.pow(monthlyRate, ((-1) * loan.getTermMonths())) ));
	   double riskFactor = BigDecimal.ONE.subtract(
			   ((!loan.getBorrower().getDelinquentDebt()? BigDecimal.valueOf(0.35) : BigDecimal.ZERO)
					   .add(( loan.getBorrower().getAnnualIncome() > 0 && 
							   ( BigDecimal.valueOf( loan.getBorrower().getAnnualDebt())
									   .divide( BigDecimal.valueOf(loan.getBorrower().getAnnualIncome()), MathContext.DECIMAL128) )
							   		   .doubleValue() < 0.43? BigDecimal.valueOf(0.3): BigDecimal.ZERO ))
					   .add((loan.getBorrower().getCreditHistory() >= 5? BigDecimal.valueOf(0.25): BigDecimal.ZERO))
					   .add(BigDecimal.valueOf(0.1))
		)).doubleValue();
	   return new LoanMetric(monthlyRate, monthlyPayment, riskFactor);
   
   }
   
}
