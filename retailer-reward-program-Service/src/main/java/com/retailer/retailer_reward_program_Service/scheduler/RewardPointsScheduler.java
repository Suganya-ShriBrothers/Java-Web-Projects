package com.retailer.retailer_reward_program_Service.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.retailer.retailer_reward_program_Service.controller.CustomerRestController;
import com.retailer.retailer_reward_program_Service.service.CustomerService;

@Component
public class RewardPointsScheduler {
	
	private static final Logger LOGGER = LogManager.getLogger(CustomerRestController.class);
	
	@Autowired
	private CustomerService customerService; 
	
	//@Scheduled(cron = "0 * 17 * * ?")
	@Scheduled(cron = "0 * 23 L * ?")
	public void scheduleRewardPointTask() {
		LOGGER.info("Enterted scheduleRewardPointTask:This Scheduler will run Every Month Last Date at 11PM to collect all Transaction details of that month");
		customerService.currentMonRewardPtsEntry(); 
	}

}
