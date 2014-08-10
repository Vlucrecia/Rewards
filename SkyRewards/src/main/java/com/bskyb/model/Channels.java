package com.bskyb.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Channels", uniqueConstraints = {
		@UniqueConstraint(columnNames = "channel_code")})

public class Channels  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "channel_code")
	private String channelCode;

	private String description;
	
	public Channels(){
		
	}
	
	public Channels(String channelCode, String description){
		this.channelCode = channelCode;
		this.description = description;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "channels")
	private Set<Rewards> rewardsList = new HashSet<Rewards>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "channels")
	private Set<CustomerPortfolio> customerPortfolioList = new HashSet<CustomerPortfolio>();

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<Rewards> getRewards() {
		return this.rewardsList;
	}
 
	public void setRewards(Set<Rewards> rewards) {
		this.rewardsList = rewards;
	}

	public Set<CustomerPortfolio> getCustomerPortfolioList() {
		return customerPortfolioList;
	}

	public void setCustomerPortfolioList(
			Set<CustomerPortfolio> customerPortfolioList) {
		this.customerPortfolioList = customerPortfolioList;
	}

}
