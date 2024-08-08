package com.retailer.retailer_reward_program_Service.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AllCustomerTransactionPerMonth {
	
	private List<CustomerTransaction> oneCustTrans;

	public List<CustomerTransaction> getOneCustTrans() {
		return oneCustTrans;
	}

	public void setOneCustTrans(List<CustomerTransaction> oneCustTrans) {
		this.oneCustTrans = oneCustTrans;
	}

}
