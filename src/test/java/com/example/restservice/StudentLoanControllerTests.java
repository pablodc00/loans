/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.restservice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.restservice.model.Borrower;
import com.example.restservice.model.Loan;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentLoanControllerTests extends TestBase{


	private static Loan loan;
	
	@BeforeAll
    public static void init(){
		loan = new Loan();
		loan.setLoanId((long) 1);
		loan.setRequestedAmount(10000);
		loan.setTermMonths(0);
		loan.setAnnualInterest(6);
	}
	
	@Test
	public void studentLoanPerfectBorrowerShouldHaveRiskFactor_0() throws Exception {
		Borrower borrower = new Borrower();
		borrower.setName("James Gosling");
		borrower.setAnnualIncome((double) 10000);
		borrower.setDelinquentDebt(false);
		borrower.setAnnualDebt((double) 3000);
		borrower.setCreditHistory(10);
		loan.setBorrower(borrower);
		loan.setType("student");
		
		testLoanMetricProperty(loan, "$.riskFactor", 0);
	}

	@Test
	public void studentLoanDelinquentBorrowerShouldHaveRiskFactor_035() throws Exception {
		Borrower borrower = new Borrower();
		borrower.setName("James Gosling");
		borrower.setAnnualIncome((double) 10000);
		borrower.setDelinquentDebt(true);
		borrower.setAnnualDebt((double) 3000);
		borrower.setCreditHistory(10);
		loan.setBorrower(borrower);
		loan.setType("student");
		
		testLoanMetricProperty(loan, "$.riskFactor", 0.35);
	}
	
	@Test
	public void studentLoanNoCreditHistoryBorrowerShouldHaveRiskFactor_025() throws Exception {
		Borrower borrower = new Borrower();
		borrower.setName("James Gosling");
		borrower.setAnnualIncome((double) 10000);
		borrower.setDelinquentDebt(false);
		borrower.setAnnualDebt((double) 0);
		borrower.setCreditHistory(0);
		loan.setBorrower(borrower);
		loan.setType("student");
		
		testLoanMetricProperty(loan, "$.riskFactor", 0.25);
	}
	
	@Test
	public void studentLoanTooMuchDebtBorrowerShouldHaveRiskFactor_040() throws Exception {
		Borrower borrower = new Borrower();
		borrower.setName("James Gosling");
		borrower.setAnnualIncome((double) 10000);
		borrower.setDelinquentDebt(false);
		borrower.setAnnualDebt((double) 30000);
		borrower.setCreditHistory(10);
		loan.setBorrower(borrower);
		loan.setType("student");
		
		testLoanMetricProperty(loan, "$.riskFactor", 0.40);
	}
	
	@Test
	public void studentLoanNoIncomeBorrowerShouldHaveRiskFactor_040() throws Exception {
		Borrower borrower = new Borrower();
		borrower.setName("James Gosling");
		borrower.setAnnualIncome((double) 100);
		borrower.setDelinquentDebt(false);
		borrower.setAnnualDebt((double) 0);
		borrower.setCreditHistory(10);
		loan.setBorrower(borrower);
		loan.setType("student");
		
		testLoanMetricProperty(loan, "$.riskFactor", 0.40);
	}
	
	@Test
	public void studentLoanTooMuchDebtAndDelinquentBorrowerShouldHaveRiskFactor_075() throws Exception {
		Borrower borrower = new Borrower();
		borrower.setName("James Gosling");
		borrower.setAnnualIncome((double) 10000);
		borrower.setDelinquentDebt(true);
		borrower.setAnnualDebt((double) 30000);
		borrower.setCreditHistory(10);
		loan.setBorrower(borrower);
		loan.setType("student");
		
		testLoanMetricProperty(loan, "$.riskFactor", 0.75);
	}
	
	@Test
	public void studentLoanTerribleBorrowerShouldHaveRiskFactor_1() throws Exception {
		Borrower borrower = new Borrower();
		borrower.setName("James Gosling");
		borrower.setAnnualIncome((double) 10000);
		borrower.setDelinquentDebt(true);
		borrower.setAnnualDebt((double) 30000);
		borrower.setCreditHistory(1);
		loan.setBorrower(borrower);
		loan.setType("student");
		
		testLoanMetricProperty(loan, "$.riskFactor", 1);
	}

}
