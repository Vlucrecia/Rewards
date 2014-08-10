package com.bskyb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rewards")
public class Rewards {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "channel_code")
	private Channels channels;

	private String reward;
	
	public Rewards(){
		
	}
	public Rewards(Channels channels, Integer id, String reward) {
		this.id = id;
		this.channels = channels;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Channels getChannels() {
		return this.channels;
	}
 
	public void setChannels(Channels channels) {
		this.channels = channels;
	}


	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	

}
