package com.retailer.retailer_reward_program_Service.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.retailer.retailer_reward_program_Service.controller.CustomerRestController;
import com.retailer.retailer_reward_program_Service.exception.CustomerAlreadyExistsException;
import com.retailer.retailer_reward_program_Service.exception.NoSuchCustomerExistsException;
import com.retailer.retailer_reward_program_Service.model.Customer;
import com.retailer.retailer_reward_program_Service.model.CustomerTransaction;
import com.retailer.retailer_reward_program_Service.model.RewardPoints;
import com.retailer.retailer_reward_program_Service.repository.CustomerRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
import com.retailer.retailer_reward_program_Service.repository.CustomerTransRepository;
import com.retailer.retailer_reward_program_Service.repository.RewardPointsRepository;

@Service
@PropertySource("classpath:appconstants.properties")
public class CustomerService {
	
	private static final Logger LOGGER = LogManager.getLogger(CustomerRestController.class);
	
	@Autowired
    private CustomerRepository custRepository;
	@Autowired
	private CustomerTransRepository custTransRepository;
	@Autowired
	private RewardPointsRepository rewardPointsRepository;
		
	//constant value attributes
	@Value("${CREATED_SUCCESSFULLY}")
	private String success ;	
	
	@Value("${TRANSACTION_ADDED}")
	private String addTrans;	
	
	@Value("${CUSTOMER_EXIST}")
	private String exist;
	
	@Value("${CUSTOMER_NOT_EXIST}")
	private String notExist;
	
	@Value("${REWARD_POINTS_ADDED}")
	private String rewardAdded;
	
	@Value("${PURCHASE_AMOUNT_MORE}")
	private String maxAmount;
	
	@Value("${PURCHASE_AMOUNT_LESS}")
	private String minAmount ;
	@Value("${REWARD_POINTS_MAX}")
	private String rewardMax;
	
	@Value("${REWARD_POINTS_MIN}")
	private String rewardMin ;
	
			
	/*@Autowired
	private PasswordEncoder passwordEncoder;*/
	
	
	//This method is used for customer Registration process
	public String registerCustomer(Customer customer) {
		LOGGER.info("Enterted registerCustomer");
		// Password Encoding using BCryptPasswordEncoder
		//customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		
		Customer newCustomer = custRepository.findByCredentials(customer.getCustName(), customer.getPassword())
				.orElse(null);
		if (newCustomer == null) {
			//save data into DB
			LOGGER.info("enterted if registerCustomer");
			customer.setDate(LocalDate.now());
			custRepository.save(customer);
			return success;
		}
		else
			throw new CustomerAlreadyExistsException(
					exist);
		
	}  
	
	//This method is used for login process
    public Customer loginCustomer(String userName, String password) {
    	LOGGER.info("Enterted loginCustomer");
    	Customer existingCustomer = custRepository.findByCredentials(userName,password)
				.orElse(null);
		if (existingCustomer == null)
						throw new NoSuchCustomerExistsException(
				  notExist);	
	    else {
			return existingCustomer;
		}
    }
    
    // This method is used for add Customer Transaction process
	public String addCustTrans(CustomerTransaction customerTransaction) {
		LOGGER.info("Enterted addCustTrans");
		customerTransaction.setDate(LocalDate.now());
		custTransRepository.save(customerTransaction);
		return addTrans;
		

	} 
	
	  //This method is used to get Customer Transaction details
		public List<CustomerTransaction> getCustTransDtls(int custId) {
			LOGGER.info("Enterted getCustTransDtls");
			List<CustomerTransaction> transList = new ArrayList<CustomerTransaction>();
			transList.addAll(custTransRepository.findByCustomerId(custId).orElse(transList));
			return transList;
		} 
		
		 //This method is used to delete Customer Transaction details
		public void deleteCusTransDtls(int custId) {
			LOGGER.info("Enterted deleteCusTransDtls");
			custTransRepository.deleteByCustomerId(custId);
		} 
		
		//This method is used to get Reward point details for the given customer
		public List<RewardPoints> getRewardPtDtls(int custId) {
			LOGGER.info("Enterted getRewardPtDtls");
			List<RewardPoints> rewardPts = new ArrayList<RewardPoints>();
			rewardPts.addAll(rewardPointsRepository.findByCustomerId(custId).orElse(rewardPts));
			return rewardPts;
		} 
		
		// This method is used to get Reward points details for all the customers
		public List<RewardPoints> getRewardPtsOfAll() {
			LOGGER.info("Enterted getRewardPtsOfAll");
			System.out.println("enterted getRewardPtsOfAll");
			return rewardPointsRepository.findAll();
		} 
		
		/* This method will called by Scheduler job at last day 
		 * of every month at 11PM.
		 * This method is used to get current month all
		 * Customer Transaction details details for calculating reward points
		 */
		public String currentMonRewardPtsEntry() {
			LOGGER.info("Enterted currentMonRewardPtsEntry");
			List<Integer> disntCustId = custTransRepository.getDisnctCustomerId();
			List<CustomerTransaction> currentMonAllTrans = new ArrayList<CustomerTransaction>();
			currentMonAllTrans.addAll(custTransRepository.getCurrentMonthTransactions().orElse(currentMonAllTrans));
			makeRewardPointsEntry(currentMonAllTrans,disntCustId);
			return rewardAdded;
		} 

		/**
		 * This method is used to fetch each customer transaction details 
		 * for calculating total purchase amount and reward points then 
		 * make entry in reward points table.
		 * @param currentMonAllTrans
		 */
		private void makeRewardPointsEntry(List<CustomerTransaction> currentMonAllTrans,List<Integer> disntCustId ) {
			LOGGER.info("Enterted makeRewardPointsEntry"+currentMonAllTrans);
			
			disntCustId.forEach(custId -> {
				LOGGER.info("Enterted makeRewardPointsEntry custId"+custId);
				
				currentMonAllTrans.stream().filter(trans -> trans.getCustId() == custId).forEach(c -> System.out.println(c));
				
				
				int totalPurchaseAmt = currentMonAllTrans.stream().filter(trans -> trans.getCustId() == custId).mapToInt(cusTrans -> cusTrans.getAmount()).sum();
				LOGGER.info("Enterted makeRewardPointsEntry totalPurchaseAmt"+totalPurchaseAmt);	
				int rewardPoints = calculateRewardPoints(totalPurchaseAmt);	
				LOGGER.info("Enterted makeRewardPointsEntry rewardPoints"+rewardPoints);	
				//create RewardPoints Table Entity
				RewardPoints rewardPointEntry = new RewardPoints(custId,
																 LocalDate.now().getMonthValue(),
																 LocalDate.now().getYear(),
						                                         rewardPoints);
				//save into DB				
				rewardPointsRepository.save(rewardPointEntry);
				
			});					
		   
		}
		
		
	//This method is used calculate the reward points per business requirement
	private int calculateRewardPoints( int purchaseAmount ) {
		LOGGER.info("Enterted calculateRewardPoints"+purchaseAmount);
		//return (purchaseAmount > 100 )?(purchaseAmount-100)*2+50*1:(purchaseAmount-50)*1;
		return ( purchaseAmount > Integer.parseInt(maxAmount) )?(purchaseAmount-Integer.parseInt(maxAmount))*Integer.parseInt(rewardMax)
				+Integer.parseInt(minAmount)*Integer.parseInt(rewardMin):
					(purchaseAmount-Integer.parseInt(minAmount))*Integer.parseInt(rewardMin);
		
	}
	
}
