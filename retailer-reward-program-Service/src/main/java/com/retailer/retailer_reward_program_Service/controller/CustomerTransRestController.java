package com.retailer.retailer_reward_program_Service.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.retailer_reward_program_Service.exception.CustomerAlreadyExistsException;
import com.retailer.retailer_reward_program_Service.model.CustomerTransaction;
import com.retailer.retailer_reward_program_Service.service.CustomerService;

@RestController
@PropertySource("classpath:appconstants.properties")
public class CustomerTransRestController {
	
	private static final Logger LOGGER = LogManager.getLogger(CustomerRestController.class);
	
	@Value("${INTERNAL_SERVER_ERROR}")
	private String serverError;
	
	@Value("${DELETE_CUSTOMER_TRANSACTION}")
	private String deleteTrans;
	
	
	@Autowired
    private CustomerService customerService; 
	
	@PostMapping("/addTransaction")
	 public String addCustomerTrans(@RequestBody(required = true) CustomerTransaction customerTransaction) throws Exception {
		try {
		return customerService.addCustTrans(customerTransaction);
		}catch(Exception ex) {
			LOGGER.error(ex.getMessage());
			throw new Exception(serverError);		
		}
	 }
	
	@GetMapping("/getTransaction/{custId}")
	 public List<CustomerTransaction> getTransactionDtls(@PathVariable(value="custId", required = true) int custId)throws Exception  {
		try {
		return customerService.getCustTransDtls(custId);
		}catch(Exception ex) {
			LOGGER.error(ex.getMessage());
			throw new Exception(serverError);		
		}
	 }
	
	@DeleteMapping("/deleteTransaction/{custId}")
	 public String deleteTransactionDtls(@PathVariable(value="custId",required=true) int custId)throws Exception {
		try {
		customerService.deleteCusTransDtls( custId);
		return deleteTrans;
		}catch(Exception ex) {
			LOGGER.error(ex.getMessage());
			throw new Exception(serverError);		
		}
	 }

}