package com.bskyb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bskyb.model.Channels;
import com.bskyb.model.CustomerPortfolio;
import com.bskyb.service.CustomerService;
import com.bskyb.service.RewardsService;

@Controller
@RequestMapping("/rewards")
public class RewardsController {
	RewardsService rewardsService;
	CustomerService customerService;

	@RequestMapping(method = RequestMethod.GET)
	public String rewards(ModelMap model) {
		model.addAttribute("showRewards", "hidden");
		return "rewards";
	}

	/**
	 * Invoke on selecting showRewards button
	 * @param model
	 * @param accountNo
	 * @param channelsSubscribed
	 * @return
	 */
	@RequestMapping(params = "showChannelsSubScribed", method = RequestMethod.POST)
	public String showRewards(
			ModelMap model,
			@RequestParam(value = "accountNo", required = false) String accountNo,
			@RequestParam(value = "channelsSubscribed", required = false) List<String> channelsSubscribed) {
		List<CustomerPortfolio> portfolio = customerService.getChannelsSubscribed(accountNo); 
		List<Channels> channelList = new ArrayList<Channels>();
		for (CustomerPortfolio custPortfolio : portfolio) {
			channelList.add(custPortfolio.getChannels());
		}
		model.addAttribute("accountNo",accountNo);
		model.addAttribute("channelsSubscribed",channelList);
		if(channelList.size()==0){
			model.addAttribute("showRewards", "hidden");
		}else{
			model.addAttribute("showRewards", "visible");
		}
		model.addAttribute("noChannels", channelList.size()==0);
		return "rewards";

	}

	/**
	 * Invoke show channels subscribed
	 * @param model
	 * @param accountNo
	 * @param channelsSubscribed
	 * @return
	 */
	@RequestMapping(params = "showRewards", method = RequestMethod.POST)
	public String channelsSubscribed(
			ModelMap model,
			@RequestParam(value = "accountNo", required = false) String accountNo,
			@RequestParam(value = "channelsSubscribed", required = false) List<String> channelsSubscribed) {
		Map<String, Object> result = rewardsService.getRewards(accountNo,channelsSubscribed);
		model.addAttribute("accountNo",accountNo);
		model.addAttribute("result", result.get("result"));
		model.addAttribute("errMsg", result.get("errMsg"));
		model.addAttribute("showRewards", "visible");
		return "list";

	}

	public void setRewardsService(RewardsService rewardsService) {
		this.rewardsService = rewardsService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

}
