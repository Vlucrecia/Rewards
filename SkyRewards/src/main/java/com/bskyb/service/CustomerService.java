package com.bskyb.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Transactional;

import com.bskyb.model.Channels;
import com.bskyb.model.CustomerPortfolio;

@Transactional
public class CustomerService {

	private SessionFactory sessionFactory;

	/**
	 * retrieve the channels Subscribed for a given account number
	 * @param accountNo
	 * @return
	 */
	public List<CustomerPortfolio> getChannelsSubscribed(String accountNo) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(CustomerPortfolio.class);
		criteria.add(Expression.eq("accountNo", accountNo));
		return criteria.list();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
