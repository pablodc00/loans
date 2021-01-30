package com.example.restservice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface LoanMetricTemplate {
   
   /**
    * Annotates the template name of Loan Metric Calculator
    * 
    * @return
    */
   public String value();

}
