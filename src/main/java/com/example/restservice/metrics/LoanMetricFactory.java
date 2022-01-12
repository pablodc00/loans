package com.example.restservice.metrics;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.metrics.impl.ConsumerLoanMetricCalculator;
import com.example.restservice.metrics.impl.StudentLoanMetricCalculator;
import com.example.restservice.model.Loan;

@Service
public class LoanMetricFactory {

	private @Autowired List<ILoanMetricCalculator> loanMetricCalculator;


	// BETTER WAY
	public ILoanMetricCalculator getInstance(Loan loan) {
				
		//TODO: use BusinessException
		Optional<ILoanMetricCalculator> o = 
				loanMetricCalculator.stream().
				filter(a -> a.isSupported(loan)).
				findFirst();
		
		if (o.isPresent()) {
			o.get();
		}
		return null;
	}
	

	//////////////////////////////////////////////////////////////
	// OLD, NOT GOOD
	public static String LOAN_TYPE_STUDENT = "student";
	public static String LOAN_TYPE_CONSUMER = "consumer";
	
	
	public ILoanMetricCalculator getInstance2(Loan loan) {
			
		if (loan.getType().equals(LOAN_TYPE_CONSUMER)) {
			return new ConsumerLoanMetricCalculator();

		}
		
		if (loan.getType().equals(LOAN_TYPE_STUDENT)) {
			return new StudentLoanMetricCalculator();
		}
		
		return null;
			
	}
}
