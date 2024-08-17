package com.retailer.retailer_reward_program_Service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.mockito.Mockito;

import com.retailer.retailer_reward_program_Service.service.CustomerService;

@Profile("test")
@Configuration
public class CustomerServiceTestConfiguration {
	
	@Bean
	public CustomerService customerService() {
		return Mockito.mock(CustomerService.class);
	}

}
