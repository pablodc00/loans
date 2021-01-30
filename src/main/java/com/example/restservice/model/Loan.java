package com.example.restservice.model;

public class Loan {
   
    private Long loanId;
    
    private double requestedAmount;
    private int termMonths;
    private double annualInterest;
    
    private String type;
    private Borrower borrower;

   public Long getLoanId() {
      return loanId;
   }

   public void setLoanId(Long loanId) {
      this.loanId = loanId;
   }

   public double getRequestedAmount() {
      return requestedAmount;
   }

   public void setRequestedAmount(double requestedAmount) {
      this.requestedAmount = requestedAmount;
   }

   public int getTermMonths() {
      return termMonths;
   }

   public void setTermMonths(int termMonths) {
      this.termMonths = termMonths;
   }

   public double getAnnualInterest() {
      return annualInterest;
   }

   public void setAnnualInterest(double annualInterest) {
      this.annualInterest = annualInterest;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public Borrower getBorrower() {
      return borrower;
   }

   public void setBorrower(Borrower borrower) {
      this.borrower = borrower;
   }

}