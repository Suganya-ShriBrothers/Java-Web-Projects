package com.retailer.retailer_reward_program_Service.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.retailer.retailer_reward_program_Service.model.Customer;
import com.retailer.retailer_reward_program_Service.model.CustomerTransaction;
import com.retailer.retailer_reward_program_Service.model.RewardPoints;


@Repository
public interface RewardPointsRepository extends JpaRepository<RewardPoints, Integer>{
	
	@Query( 
	        nativeQuery = true, 
	        value ="SELECT * FROM reward_points WHERE cust_id=:custId")
	Optional<List<RewardPoints>> findByCustomerId(@Param("custId") int custId);

}
