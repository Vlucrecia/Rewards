package com.bskyb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Customer_Portfolio")
public class CustomerPortfolio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "account_No")
	private String accountNo;

	@ManyToOne
	@JoinColumn(name = "channel_code", nullable = false)
	private Channels channels;
	public CustomerPortfolio(){
		
	}
	public CustomerPortfolio(Integer id, String accountNo, Channels channels){
		this.id= id;
		this.accountNo= accountNo;
		this.channels= channels;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Channels getChannels() {
		return this.channels;
	}
 
	public void setChannels(Channels channels) {
		this.channels = channels;
	}

}
