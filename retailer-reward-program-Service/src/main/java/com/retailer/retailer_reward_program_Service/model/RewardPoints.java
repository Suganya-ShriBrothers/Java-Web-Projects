package com.retailer.retailer_reward_program_Service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="reward_points")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class RewardPoints {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rewardId;
	
	@Column(name="cust_id")
	private int custId;
	
	@Column(name="month")
	private int month;
	
	@Column(name="year")
	private int year;	

	@Column(name="points")
	private int points;
	

	public int getRewardId() {
		return rewardId;
	}

	public void setRewardId(int rewardId) {
		this.rewardId = rewardId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	
	/**
	 * Default Constructor
	 */
	public RewardPoints() {}
	/**
	 * @param custId
	 * @param month
	 * @param year
	 * @param points
	 */
	public RewardPoints(int custId, int month, int year, int points) {
		super();
		this.custId = custId;
		this.month = month;
		this.year = year;
		this.points = points;
	}
	
	

}
