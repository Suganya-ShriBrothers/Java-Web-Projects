package com.retailer.retailer_reward_program_Service.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.retailer.retailer_reward_program_Service.model.RewardPoints;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerServiceTest {	

  @Autowired
  private CustomerService customerService;

    @Test 
  void getRewardPtsOfAll()
    {
     
    	RewardPoints reward1= new RewardPoints(1,6,2024,1050);
    	RewardPoints reward2= new RewardPoints(2,7,2024,690);
        Mockito.when(customerService.getRewardPtsOfAll()).thenReturn(List.of(reward1,reward2));
        var  rewardPtList = customerService.getRewardPtsOfAll();
        assertThat(rewardPtList).isNotNull();
        assertThat(rewardPtList.size()).isEqualTo(2);
    }

}
