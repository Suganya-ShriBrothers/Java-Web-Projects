package com.retailer.retailer_reward_program_Service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.retailer.retailer_reward_program_Service.model.AllCustomerTransactionPerMonth;
import com.retailer.retailer_reward_program_Service.model.CustomerTransaction;


@Repository
public interface CustomerTransRepository extends JpaRepository<CustomerTransaction, Integer>{
	
	@Query( 
	        nativeQuery = true, 
	        value ="SELECT * FROM customer_transaction WHERE cust_id=:custId")
	Optional<List<CustomerTransaction>> findByCustomerId(@Param("custId") int custId);
	
	@Query( 
	        nativeQuery = true, 
	        value ="SELECT * FROM customer_transaction WHERE purchase_date BETWEEN concat(YEAR(CURRENT_DATE()), '-0',MONTH(CURRENT_DATE()), '-01')  AND CURRENT_DATE() order by cust_id")
	Optional<List<CustomerTransaction>> getCurrentMonthTransactions();
	
	@Query( 
	        nativeQuery = true, 
	        value ="DELETE FROM customer_transaction WHERE cust_id=:custId")
	void deleteByCustomerId(@Param("custId") int custId);
	
	@Query( 
	        nativeQuery = true, 
	        value ="SELECT DISTINCT(cust_id) FROM customer_transaction")
	List<Integer> getDisnctCustomerId();
}
