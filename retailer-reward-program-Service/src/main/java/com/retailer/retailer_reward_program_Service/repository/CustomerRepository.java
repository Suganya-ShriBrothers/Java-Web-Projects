package com.retailer.retailer_reward_program_Service.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.retailer.retailer_reward_program_Service.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query( 
	        nativeQuery = true, 
	        value ="SELECT * FROM Customer WHERE cust_name=:userName AND password=:password")
	Optional<Customer> findByCredentials(@Param("userName") String userName, @Param("password") String password);
 
}

