package com.retailer.retailer_reward_program_Service.dto;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CustomerTransactionDTO {
		
	private String custId;
	private String spentDls;
	private int amount;
	private LocalDate Date;	
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getSpentDls() {
		return spentDls;
	}
	public void setSpentDls(String spentDls) {
		this.spentDls = spentDls;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return Date;
	}
	public void setDate(LocalDate date) {
		Date = date;
	}


}
