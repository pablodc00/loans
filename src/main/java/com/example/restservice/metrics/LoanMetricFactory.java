package com.example.restservice.metrics;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.model.Loan;

@Service
public class LoanMetricFactory {

	private @Autowired List<ILoanMetricCalculator> loanMetricCalculator;


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
	

}
