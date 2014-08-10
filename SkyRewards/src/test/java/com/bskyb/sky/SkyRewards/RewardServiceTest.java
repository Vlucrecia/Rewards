package com.bskyb.sky.SkyRewards;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bskyb.model.Channels;
import com.bskyb.model.Rewards;
import com.bskyb.service.RewardsService;
import com.bskyb.util.EligibilityEnum;
import com.bskyb.webservice.WebServiceConsumer;
 

public class RewardServiceTest {
 
    @Mock
	private SessionFactory sessionFactory;

	@Mock
	Session session;

	@Mock
	Criteria criteria;
	@Mock
	WebServiceConsumer webServiceConsumer;
	
	Rewards rewards;
 
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
		
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createCriteria(Rewards.class)).thenReturn(criteria);
		Channels channels = new Channels("1", "SPORTS");
		rewards = new Rewards(channels,1, "CHAMPIONS_LEAGUE_FINAL_TICKET");
		List<Rewards> result = new ArrayList<Rewards>();
		result.add(rewards);
		when(criteria.list()).thenReturn(result);
    }
 
    /**
     * Test for Eligibile customers
     */
    @Test
    public void testEligibilityWithCorrectAccounID() {
    	List<String> channels = new ArrayList<String>();
    	channels.add("SPORTS");
    	when(
				webServiceConsumer.invoke("/eligibility/100",
						EligibilityEnum.class)).thenReturn(
				EligibilityEnum.CUSTOMER_ELIGIBLE);
		RewardsService rewardService = new RewardsService();
		rewardService.setWebServiceConsumer(webServiceConsumer);
		rewardService.setSessionFactory(sessionFactory);

		Map<String, Object> result = rewardService.getRewards("100", channels);
		List<Rewards> queryList = new ArrayList<Rewards>();
		queryList.add(rewards);
		assertEquals(result.get("result"), queryList);
		assertEquals(result.get("errMsg"), null);
    }
    
    /**
     * Test for InEligibile customers
     */
    @Test
    public void testInEligibilityWithCorrectAccounID() {
    	List<String> channels = new ArrayList<String>();
    	channels.add("SPORTS");
    	when(
				webServiceConsumer.invoke("/eligibility/100",
						EligibilityEnum.class)).thenReturn(
				EligibilityEnum.CUSTOMER_INELIGIBLE);
		RewardsService rewardService = new RewardsService();
		rewardService.setWebServiceConsumer(webServiceConsumer);
		rewardService.setSessionFactory(sessionFactory);

		Map<String, Object> result = rewardService.getRewards("100", channels);
		assertEquals(result.get("result"), null);
		assertEquals(result.get("errMsg"), null);
    }
    
    /**
     * Test for InCorrect Account Number
     */
    @Test
    public void testInCorrecttAccounID() {
    	List<String> channels = new ArrayList<String>();
    	channels.add("SPORTS");
    	when(
				webServiceConsumer.invoke("/eligibility/100",
						EligibilityEnum.class)).thenReturn(
				EligibilityEnum.INVALID_ACCOUNT_NUMBER);
		RewardsService rewardService = new RewardsService();
		rewardService.setWebServiceConsumer(webServiceConsumer);
		rewardService.setSessionFactory(sessionFactory);

		Map<String, Object> result = rewardService.getRewards("100", channels);
		assertEquals(result.get("result"), null);
		assertEquals(result.get("errMsg"), EligibilityEnum.INVALID_ACCOUNT_NUMBER.description);
    }
    
    
}
