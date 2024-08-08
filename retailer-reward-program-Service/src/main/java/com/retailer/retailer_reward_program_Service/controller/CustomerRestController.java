package com.retailer.retailer_reward_program_Service.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.retailer_reward_program_Service.exception.CustomerAlreadyExistsException;
import com.retailer.retailer_reward_program_Service.exception.NoSuchCustomerExistsException;
import com.retailer.retailer_reward_program_Service.model.Customer;
import com.retailer.retailer_reward_program_Service.service.CustomerService;


@RestController
@PropertySource("classpath:appconstants.properties")
public class CustomerRestController{
	
	private static final Logger LOGGER = LogManager.getLogger(CustomerRestController.class);
	@Value("${CUSTOMER_EXIST}")
	private String exist;
	
	@Value("${CUSTOMER_NOT_EXIST}")
	private String notExist ;

	@Autowired
	private CustomerService customerService; 

	//This method is used for adding customer details
	@PostMapping("/addcustomer")
	public String addCustomer(@RequestBody(required = true) Customer customer) {
		LOGGER.info("Enterted addCustomer");
		try {
		return customerService.registerCustomer(customer);
		}catch(CustomerAlreadyExistsException ex) {
			LOGGER.error(ex.getMessage());
			throw new CustomerAlreadyExistsException(exist);		
		}
	}

	//This method is used for login customer
	@GetMapping("/login/{userName}/{password}")
	private ResponseEntity<Customer> loginCustomer(@PathVariable(value = "userName", required = true) String userName, @PathVariable(value = "password", required = true) String password) {
		LOGGER.info("Enterted loginCustomer");
		try {
		return new ResponseEntity<Customer>(customerService.loginCustomer(userName, password), HttpStatus.CREATED);		
		}catch(	NoSuchCustomerExistsException ex) {
			LOGGER.error(ex.getMessage());
			throw new NoSuchCustomerExistsException(exist);
		}
      
	}


	

}
