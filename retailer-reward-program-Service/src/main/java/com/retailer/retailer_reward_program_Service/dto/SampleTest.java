package com.retailer.retailer_reward_program_Service.dto;

import java.util.List;
import java.util.stream.*;

public class SampleTest {
	
	
		public static void main(String args[]) {
		List<Integer> ss = List.of(1,2,3,3,5,6);
		int i = 3;
		
		ss.stream().filter(s -> s == 3).collect(Collectors.toList()).forEach(s ->System.out.println(s));
		}

}
