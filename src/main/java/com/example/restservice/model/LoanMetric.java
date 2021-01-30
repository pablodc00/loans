package com.example.restservice.model;

public class LoanMetric {
   
    private Double monthlyInterestRate;
    private Double monthlyPayment;
    private Double riskFactor;
    
    public LoanMetric(double monthlyInterestRate, double monthlyPayment, double riskFactor) {
    	this.monthlyInterestRate = monthlyInterestRate;
    	this.monthlyPayment = monthlyPayment;
    	this.riskFactor = riskFactor;
    }

   public Double getMonthlyInterestRate() {
      return monthlyInterestRate;
   }

   public void setMonthlyInterestRate(Double monthlyInterestRate) {
      this.monthlyInterestRate = monthlyInterestRate;
   }

   public Double getMonthlyPayment() {
      return monthlyPayment;
   }

   public void setMonthlyPayment(Double monthlyPayment) {
      this.monthlyPayment = monthlyPayment;
   }

   public Double getRiskFactor() {
      return riskFactor;
   }

   public void setRiskFactor(Double riskFactor) {
      this.riskFactor = riskFactor;
   }

}