package com.example.restservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.restservice.model.Loan;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestBase {
	
	@Autowired
	protected MockMvc mockMvc;
	
	public void testLoanMetricProperty(Loan loan, String property, double value) throws Exception {
		postLoanMetric(loan)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath(property).value(value));
	}
	
	public ResultActions postLoanMetric(Loan loan) throws Exception {
		return this.mockMvc.perform(
				 post("/loanmetric")
				 	.contentType(MediaType.APPLICATION_JSON)
				 	.content(asJsonString(loan)));
	}
	

	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
