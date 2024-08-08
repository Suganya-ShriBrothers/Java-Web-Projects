package com.retailer.retailer_reward_program_Service.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.retailer_reward_program_Service.model.RewardPoints;
import com.retailer.retailer_reward_program_Service.service.CustomerService;

@RestController
@PropertySource("classpath:appconstants.properties")
public class PurchaseRestController {
	private static final Logger LOGGER = LogManager.getLogger(CustomerRestController.class);
	
	@Value("${INTERNAL_SERVER_ERROR}")
	private String serverError;
	
	
	@Autowired
    private CustomerService customerService; 
	
	@GetMapping("/getRewardPoints/{custId}")
	 public List<RewardPoints> getTransactionDtls(@PathVariable(value="custId",required = true) int custId)throws Exception {
		try {
		return customerService.getRewardPtDtls(custId);
		}catch(Exception ex) {
			LOGGER.error(ex.getMessage());
			throw new Exception(serverError);		
		}
	 }
	
	@GetMapping("/getRewardPointsAll")
	 public List<RewardPoints> deleteTransactionDtls()throws Exception {
		try {
		return customerService.getRewardPtsOfAll();
		}catch(Exception ex) {
			LOGGER.error(ex.getMessage());
			throw new Exception(serverError);		
		}
	 }

}
