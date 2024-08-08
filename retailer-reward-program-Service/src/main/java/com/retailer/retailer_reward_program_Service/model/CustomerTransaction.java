package com.retailer.retailer_reward_program_Service.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="customer_transaction")
//@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transId;
	
	@Column(name="cust_id")
	private int custId;
	
	@Column(name="spent_dls")
	private String spentDls;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="purchase_date")
	private LocalDate date;
	
	
	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
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
		return date;
	}

	public void setDate(LocalDate date) {
		date = date;
	}

	
}
