package com.retailer.retailer_reward_program_Service.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Customer")
//@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cust_id")
	private int custId;	
	
	@NotBlank(message = "Enter a valid UserName")
	@Column(name="cust_name")
	private String custName;
	
	@Column(name="password")
	private String password;
	
	
	//@Size(min=10,max=10, message="Enter valid Phone Number with 10 Digits")
	@Column(name="phone_num")
	private int phoneNum;	
	
	@Email(message = "Please enter a valid email Id")
	@Column(name="email")
	private String email;	
	
	//@Size( min = 5, max = 20,message= "Address should have a length between 5 and 20 characters.")
	@Column(name="address")
	private String address;
	
	@Column(name="latest_login_date")
	private LocalDate Date;
	
	/*@Column(name="RewardEntry")
	private int rewardMonthEntry;*/	
	
	
	
	@OneToMany
	@JoinColumn(name="cust_id", referencedColumnName="cust_id")
	private List<CustomerTransaction> transactions;
	
	@OneToMany
	@JoinColumn(name="cust_id", referencedColumnName="cust_id")
	private List<RewardPoints> rewardPoints;
	

	public int getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<CustomerTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<CustomerTransaction> transactions) {
		this.transactions = transactions;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDate() {
		return Date;
	}

	public void setDate(LocalDate date) {
		Date = date;
	}

	/*public int getRewardMonthEntry() {
		return rewardMonthEntry;
	}

	public void setRewardMonthEntry(int rewardMonthEntry) {
		this.rewardMonthEntry = rewardMonthEntry;
	}*/

	public List<RewardPoints> getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(List<RewardPoints> rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

}
