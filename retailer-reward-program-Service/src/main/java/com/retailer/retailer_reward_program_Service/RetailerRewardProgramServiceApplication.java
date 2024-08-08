package com.retailer.retailer_reward_program_Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RetailerRewardProgramServiceApplication {
	
	private static final Logger LOGGER = LogManager.getLogger(RetailerRewardProgramServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RetailerRewardProgramServiceApplication.class, args);
		LOGGER.info("@@@@@@@@ Hello Spring RetailerRewardProgramServiceApplication @@@@ !!");
	}

}

