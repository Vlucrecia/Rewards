package com.bskyb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.springframework.transaction.annotation.Transactional;

import com.bskyb.model.Rewards;
import com.bskyb.util.EligibilityEnum;
import com.bskyb.webservice.WebServiceConsumer;

@Transactional
public class RewardsService {

	private SessionFactory sessionFactory;

	private WebServiceConsumer webServiceConsumer;

	/**
	 * Retrieve rewards if the customer is eligible
	 * @param accountNo
	 * @param channelsSubscribed
	 * @return
	 */
	public Map<String, Object> getRewards(String accountNo,List<String> channelsSubscribed) {
		EligibilityEnum response = webServiceConsumer.invoke("/eligibility/"
				+ accountNo, EligibilityEnum.class);
		Map<String, Object> result = new HashMap<String, Object>();
		if(response != null){
			if(response == EligibilityEnum.CUSTOMER_ELIGIBLE){
				Session session = sessionFactory.getCurrentSession();
				Criteria criteria = session.createCriteria(Rewards.class);
				criteria.add(Expression.in("channels.channelCode", channelsSubscribed));
				result.put("result", criteria.list());
			}else if(response == EligibilityEnum.INVALID_ACCOUNT_NUMBER){
				result.put("errMsg",
						EligibilityEnum.INVALID_ACCOUNT_NUMBER.description);
			}
		}
		return result;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setWebServiceConsumer(WebServiceConsumer webServiceConsumer) {
		this.webServiceConsumer = webServiceConsumer;
	}

}
